﻿//=============================================================================
/**
 * Created by clock on 16-11-25.
 */
var conn = {};
conn = new WebIM.connection({
    isMultiLoginSessions: WebIM.config.isMultiLoginSessions,
    https: typeof WebIM.config.https === 'boolean' ? WebIM.config.https : location.protocol === 'https:',
    url: WebIM.config.xmppURL,
    isAutoLogin: true,
    heartBeatWait: WebIM.config.heartBeatWait,
    autoReconnectNumMax: WebIM.config.autoReconnectNumMax,
    autoReconnectInterval: WebIM.config.autoReconnectInterval
});

// tips: ie8 support  fileInputId should match with the file in the document
WebIM.flashUpload = UploadShim({fileInputId: 'image'}, conn).flashUpload;


// listern，添加回调函数
conn.listen({
    onOpened: function (message) {          //连接成功回调，连接成功后才可以发送消息
        //如果isAutoLogin设置为false，那么必须手动设置上线，否则无法收消息
        // 手动上线指的是调用conn.setPresence(); 在本例中，conn初始化时已将isAutoLogin设置为true
        // 所以无需调用conn.setPresence();
        console.log("%c [opened] 连接已成功建立", "color: green")
    },
    onTextMessage: function (message) {
        // 在此接收和处理消息，根据message.type区分消息来源，私聊或群组或聊天室
        console.log(message);
        console.log(message.type);
        console.log('Text');
    },  //收到文本消息
    onEmojiMessage: function (message) {
        // 当为WebIM添加了Emoji属性后，若发送的消息含WebIM.Emoji里特定的字符串，connection就会自动将
        // 这些字符串和其它文字按顺序组合成一个数组，每一个数组元素的结构为{type: 'emoji(或者txt)', data:''}
        // 当type='emoji'时，data表示表情图像的路径，当type='txt'时，data表示文本消息
        console.log('Emoji');
        var data = message.data;
        for (var i = 0, l = data.length; i < l; i++) {
            console.log(data[i]);
        }
    },   //收到表情消息
    onPictureMessage: function (message) {
        console.log('Picture');

        var options = {url: message.url};
        options.onFileDownloadComplete = function () {
            // 图片下载成功
            console.log('Image download complete!');
        };
        options.onFileDownloadError = function () {
            // 图片下载失败
            console.log('Image download failed!');
        };
        WebIM.utils.download.call(conn, options);       // 意义待查

    }, //收到图片消息
    onCmdMessage: function (message) {
        console.log('CMD');
    },     //收到命令消息
    onAudioMessage: function (message) {
        console.log("Audio");
    },   //收到音频消息
    onLocationMessage: function (message) {
        console.log("Location");
    },//收到位置消息
    onFileMessage: function (message) {
        console.log("File");
    },    //收到文件消息
    onVideoMessage: function (message) {
        var node = document.getElementById('privateVideo');
        var option = {
            url: message.url,
            headers: {
                'Accept': 'audio/mp4'
            },
            onFileDownloadComplete: function (response) {
                var objectURL = WebIM.utils.parseDownloadResponse.call(conn, response);
                node.src = objectURL;
            },
            onFileDownloadError: function () {
                console.log('File down load error.')
            }
        };
        WebIM.utils.download.call(conn, option);
    },   //收到视频消息
    onPresence: function (message) {
        switch (message.type) {
            case 'subscribe':                           // 对方请求添加好友
                // 同意对方添加好友
                document.getElementById('agreeFriends').onclick = function (message) {
                    conn.subscribed({
                        to: 'asdfghj',
                        message: "[resp:true]"
                    });
                };
                // 拒绝对方添加好友
                document.getElementById('rejectFriends').onclick = function (message) {
                    conn.unsubscribed({
                        to: message.from,
                        message: "rejectAddFriend"                  // 拒绝添加好友回复信息
                    });
                };

                break;
            case 'subscribed':                          // 对方同意添加好友，已方同意添加好友
                break;
            case 'unsubscribe':                         // 对方删除好友
                break;
            case 'unsubscribed':                        // 被拒绝添加好友，或被对方删除好友成功
                break;
            case 'memberJoinPublicGroupSuccess':                 // 成功加入聊天室
                console.log('join chat room success');
                break;
            case 'joinChatRoomFaild':                   // 加入聊天室失败
                console.log('join chat room faild');
                break;
            case 'joinPublicGroupSuccess':              // 意义待查
                console.log('join public group success', message.from);
                break;
            case 'createGroupACK':
                conn.createGroupAsync({
                    from: message.from,
                    success: function (option) {
                        console.log('Create Group Succeed');
                    }
                });
                break;
        }
    },       //收到联系人订阅请求（加好友）、处理群组、聊天室被踢解散等消息
    onRoster: function (message) {
        console.log('Roster');
    },         //处理好友申请
    onInviteMessage: function (message) {
        console.log('Invite');
    },  //处理群组邀请
    onOnline: function () {
        console.log('onLine');
    },                  //本机网络连接成功
    onOffline: function () {
        console.log('offline');
    },                 //本机网络掉线
    onError: function (message) {
        console.log('Error');
        console.log(message);
        if (message && message.type == 1) {
            console.warn('连接建立失败！请确认您的登录账号是否和appKey匹配。')
        }
    },           //失败回调
    onBlacklistUpdate: function (list) {
        // 查询黑名单，将好友拉黑，将好友从黑名单移除都会回调这个函数，list则是黑名单现有的所有好友信息
        console.log(list);
    }     // 黑名单变动
});
// 初始化WebRTC Call
var rtcCall = null
if (WebIM.WebRTC) {
    rtcCall = new WebIM.WebRTC.Call({
        connection: conn,

        mediaStreamConstaints: {
            audio: true,
            video: true
        },

        listener: {
            onAcceptCall: function (from, options) {
                console.log('onAcceptCall::', 'from: ', from, 'options: ', options);
            },
            onGotRemoteStream: function (stream, streamType) {
                console.log('onGotRemoteStream::', 'stream: ', stream, 'streamType: ', streamType);
                var video = document.getElementById('video');
                video.srcObject = stream;
            },
            onGotLocalStream: function (stream, streamType) {
                console.log('onGotLocalStream::', 'stream:', stream, 'streamType: ', streamType);
                var video = document.getElementById('localVideo');
                video.srcObject = stream;
            },
            onRinging: function (caller) {
                console.log('onRinging::', 'caller:', caller);
            },
            onTermCall: function (reason) {
                console.log('onTermCall::');
                console.log('reason:', reason);
            },
            onIceConnectionStateChange: function (iceState) {
                console.log('onIceConnectionStateChange::', 'iceState:', iceState);
            },
            onError: function (e) {
                console.log(e);
            }
        }
    });
} else {
    console.warn('不能进行视频通话！您的浏览器不支持webrtc或者协议不是https。')
}
//=============================================================================
/**
 * Created by clock on 16-11-30.
 */
    // open，登录
var options = {
        apiUrl: WebIM.config.apiURL,
        user: "huicy",
        pwd: "123456",
        appKey: WebIM.config.appkey
    };


var register = function () {
    var option = {
        username: 'hcy',
        password: 'admin123',
        nickname: 'clock',
        appKey: WebIM.config.appkey,
        success: function () {
            console.log('regist success!');
        },
        error: function () {
            console.log('regist error');
        },
        apiUrl: WebIM.config.apiURL
    };
    conn.signup(option);
};

// 私聊发送文本消息，发送表情同发送文本消息，只是会在对方客户端将表情文本进行解析成图片
var sendPrivateText = function () {
    var id = conn.getUniqueId();
    var msg = new WebIM.message('txt', id);
    msg.set({
        msg: 'scarecrow',                       // 消息内容
        to: 'hcy',                          // 接收消息对象
        roomType: false,
        success: function (id, serverMsgId) {
            console.log("send private text Success");
        }
    });
    msg.body.chatType = 'singleChat';
    conn.send(msg.body);
};

// 私聊发送命令消息
var sendPrivateCmd = function () {
    var id = conn.getUniqueId();
    var msg = new WebIM.message('cmd', id);
    msg.set({
        msg: 'ls',
        to: 'asdfghj',
        roomType: false,
        success: function (id, serverMsgId) {
            console.log('CMD Success');
        }
    });
    msg.body.chatType = 'singleChat';
    conn.send(msg.body);
};

// 私聊发送图片消息
var sendPrivateImg = function () {
    var id = conn.getUniqueId();
    var msg = new WebIM.message('img', id);
    var input = document.getElementById('image');               // 选择图片的input
    var file = WebIM.utils.getFileUrl(input);                   // 将图片转化为二进制文件
    var allowType = {
        'jpg': true,
        'gif': true,
        'png': true,
        'bmp': true
    };

    var option = {
        apiUrl: WebIM.config.apiURL,
        file: file,
        to: 'asdfghj',
        roomType: false,
        chatType: 'singleChat',
        onFileUploadError: function () {
            console.log('onFileUploadError');
        },
        onFileUploadComplete: function () {
            console.log('onFileUploadComplete');
        },
        success: function () {
            console.log('Success');
        },
    };
    // for ie8
    try {
        if (!file.filetype.toLowerCase() in allowType) {
            console.log('file type error')
            return
        }
    } catch (e) {
        option.flashUpload = WebIM.flashUpload
    }
    msg.set(option);
    conn.send(msg.body);
};

// 获取好友列表
var getRoasters = function () {
    var option = {
        success: function (roster) {
            // roster是所有好友，格式为：
            /*
             [
             {
             jid:"easemob#chatdemoui_test1@easemob.com",
             name:"test1",
             subscription: "both"
             // subscription的值的集合是{both, to, from, none},
             // both表示互相在对方的好友列表中，
             // to 和 from意义待定
             // 如果添加对方为好友被拒绝则为none
             }
             ]
             */
            for (var o in roster) {
                console.log("jid: ", roster[o].jid);
                console.log("name: ", roster[o].name);
                console.log("subscription: ", roster[o].subscription);
            }
        }
    };
    conn.getRoster(option);
};

// 添加好友
var addFriends = function () {
    conn.subscribe({
        to: "asdfghj",
        message: "Hello World!"                   // Demo里面接收方没有展现出来这个message，在status字段里面
    });
};

// 删除好友
var removeFriends = function () {
    conn.removeRoster({
        to: 'asdfghj',
        success: function () {
            conn.unsubscribed({
                to: 'asdfghj'
            });
        },
        error: function () {
        }
    });
};

// 拉黑好友，对方好友列表依然可以看到已方，但无法向已方发送消息
// list的结构为{username_1: {}, username_2: {}...}，拉黑好友需要将拉黑后的黑名单里的好友信息全部传入，
// 如黑名单此时已有A，B两位好友，现想将C也拉进黑名单，正确的操作是同时将ABC的信息都传入接口中。
/*
 var list = {
 username_1:{
 jid: 'appKey_'+username_1+'@easemob.com',
 name: username_1,
 subscription: 'both',
 order: 2
 },
 username_2:{
 jid: 'appKey_'+username_2+'@easemob.com',
 name: username_2,
 subscription: 'both',
 order: 3,
 type: 'jid'
 },
 username_3:{
 jid: 'appKey_'+username_3+'@easemob.com',
 name: username_3,
 subscription: 'both',
 order: 4,
 type: 'jid'
 }
 }
 jid, username, subscription均在获取好友列表时已获取到，用户可根据好友列表动态获取这些参数，
 order不重复即可
 */
var addToBlackList = function () {
    var list = {
        // user_1
        asdfghj: {
            jid: "easemob-demo#chatdemoui_asdfghj@easemob.com",
            name: "asdfghj",
            subscription: 'both',
            order: 2,
            type: 'jid'
        },
        // user_2
        wjy6: {
            jid: "easemob-demo#chatdemoui_wjy6@easemob.com",
            name: "wjy6",
            subscription: 'both',
            order: 3,
            type: 'jid'
        }
    };
    conn.addToBlackList({
        list: list,
        type: 'jid',
        success: function () {
            console.log('Add friend to black list success');
        },
        error: function () {
            console.log('Add friend to black list error');
        }
    });
}

// 获取好友黑名单，调用这个函数会回调conn.listen里的onBlacklistUpdate函数，具体细节请参照test.js
var getBlackList = function () {
    conn.getBlacklist();
};

// 将好友从黑名单拉出来
var removeBlackList = function () {
    var list = {};
    conn.removeFromBlackList({
        list: list,
        type: 'jid',
        success: function () {
            console.log('Remove from black list success.');
        },
        error: function () {
            console.log('Remove from black list error.')
        }
    });
};

// 聊天室发送文本消息
var sendRoomText = function () {
    // 退出聊天室
    var id = conn.getUniqueId();
    var msg = new WebIM.message('txt', id);
    var option = {
        msg: 'scarecrfow',                       // 消息内容
        to: '114715680632209992',               // 接收消息对象(本例为twy-room1聊天室)
        roomType: true,
        chatType: 'chatRoom',
        success: function () {
            console.log('send room text success');
        },
        fail: function () {
            console.log('failed');
        }
    };
    msg.set(option);
    msg.setGroup('groupchat');
    conn.send(msg.body);
};
// 加入聊天室
var joinRoom = function () {
    // 加入聊天室
    conn.joinChatRoom({
        roomId: "114715680632209992"
    });
};
// 退出聊天室
var quitRoom = function () {
    // 退出聊天室
    conn.quitChatRoom({
        roomId: "114715680632209992"
    });
};
// 列出所有聊天室，支持分页查询
var listRooms = function () {
    var option = {
        apiUrl: 'https://a1.easemob.com',
        pagenum: 1,                                 // 页数
        pagesize: 20,                               // 每页个数
        success: function (list) {
            console.log(list);
        },
        error: function () {
            console.log('List chat room error');
        }
    };
    conn.getChatRooms(option);
}

// 群组发送文本消息
var sendGroupText = function () {
    var id = conn.getUniqueId();
    var msg = new WebIM.message('txt', id);
    var option = {
        msg: 'scarecrfow',                       // 消息内容
        to: '1480567165764',                     // 接收消息对象(本例为Test聊天室)
        roomType: false,
        chatType: 'chatRoom',
        success: function () {
            console.log('send room text success');
        },
        fail: function () {
            console.log('failed');
        }
    };
    msg.set(option);
    msg.setGroup('groupchat');
    conn.send(msg.body);
};
// 建立一个群组
var createGroup = function () {
    var option = {
        subject: 'groupName',                       // 群名称
        description: 'create a group test',         // 群简介
        members: ['wjy6', 'asdfghj'],               // 以数组的形式存储需要加群的好友ID
        optionsPublic: true,                        // 允许任何人加入
        optionsModerate: false,                     // 加入需审批
        optionsMembersOnly: false,                  // 不允许任何人主动加入
        optionsAllowInvites: false                  // 允许群人员邀请
    };
    conn.createGroup(option);
}
// 获取群组信息（应用场景：判断用户是否为该群管理员）
var queryGroupInfo = function () {
    conn.queryRoomInfo({
        roomId: '1480747027186',
        // settings 表示入群的权限，具体值待定
        // members[0]里面包含群主信息，其结构为{affiliation: "owner", jid: appKey + "_" + username + "@easemob.com"}
        // jid中的username就是群主ID
        // fields的结构为：
        /*
         {
         affiliations: "2",                  // 意义待查
         description: "12311231313",         // 群简介
         maxusers: "200",                    // 群最大成员容量
         name: "123",                        // 群名称
         occupants: "2",                     // 意义待查
         owner: "easemob-demo#chatdemoui_mengyuanyuan"               // 群主jid
         }
         */
        success: function (settings, members, fields) {
            console.log('settings: ', settings);
            console.log('members: ', members);
            console.log('fields: ', fields);
        },
        error: function (e) {
            console.log('Error!', e);
        }
    });
};
// 成员主动离开群
var leaveGroup = function () {
    var option = {
        to: 'asdfghj',
        roomId: '1480747027186',
        success: function () {
            console.log('You leave room succeed!');
        },
        error: function () {
            console.log('Leave room faild');
        }
    };
    conn.leaveGroupBySelf(option);
};
// 将成员踢出群(同将群成员拉入群黑名单)
var addToGroupBlackList = function () {
    var option = {
        affiliation: "owner",                       // 写死
        roomId: "1480756943693",                    // 群组ID
        success: function () {
            console.log('add to black list succeed');
        },
        to: 'asdfghj'                               // 需要删除的成员ID
    };
    conn.addToGroupBlackList(option);
};
// 列出所有群组
var listGroups = function () {
    var option = {
        success: function (rooms) {
            console.log(rooms);
        },
        error: function () {
            console.log('List chat rooms error');
        }
    };
    conn.listRooms(option);
};
// 修改群信息
var changeGroupInfo = function () {
    var option = {
        roomId: '1480756943693',
        subject: 'ChangeTest',
        description: 'Change group information test',
        success: function () {
            console.log("Change Group Names Success!");
        }
    };
    conn.changeGroupSubject(option);
};
// 获取群组黑名单
var getGroupBlackList = function () {
    var option = {
        roomId: '1480758709661',
        success: function (list) {
            console.log('Get group black list: ', list);
        },
        error: function () {
            console.log('Get group black list error.');
        }
    };
    conn.getGroupBlacklist(option);
};
// 解散一个群组
var destroyGroup = function () {
    var option = {
        reason: 'Test Destroy Group',
        roomId: '1480840256052',
        success: function () {
            console.log('Destroy group success!');
        }
    };
    conn.destroyGroup(option);
};
// 加好友入群
var addGroupMembers = function () {
    var option = {
        list: ['asdfghj', 'wjy6'],
        roomId: '1480841456167'
    };
    conn.addGroupMembers(option);
};
// 将好友从黑名单移除
var removeFromGroupBlackList = function () {
    var option = {
        roomId: '1480841456167',
        to: 'wjy6',
        success: function () {
            console.log('Remove from black list success!');
        }
    };
    conn.removeGroupMemberFromBlacklist(option);
};

// 查询群组成员，此方法亦可查询聊天室成员
// 查询出来的member的结构为{affiliation: 'member', jid: "easemob-demo#chatdemoui_wjy6@easemob.com"}
// 注意，这里的jid格式，成员的用户名是chatdemoui_之后，@easemob.com之前的字符串，如本例的wjy6是用户名
var queryRoomMember = function () {
    var member = '';
    conn.queryRoomMember({
        roomId: '114715680632209992',
        success: function (members) {
            for (var o in members) {
                member = members[o];
                console.log(member);
            }
        }
    });
};

/*
 * WebRTC
 */
// 视频呼叫对方
var call = function () {
    rtcCall.caller = 'wenke123';
    rtcCall.makeVideoCall('asdfghj');
};
// 关掉/拒绝视频
var endCall = function () {
    rtcCall.endCall();
}
// 接受对方呼叫
var acceptCall = function () {
    rtcCall.acceptCall();
}

// 语音呼叫对方
var audioCall = function () {
    console.log('Audio Call');
    rtcCall.caller = 'wenke123';
    rtcCall.makeVoiceCall('asdfghj');
};

//
var logout = function () {
//        conn.clear();
    conn.close('logout');
    conn.errorType = WebIM.statusCode.WEBIM_CONNCTION_CLIENT_LOGOUT;


}

var reConn = function () {
    //appkey： easemob-demo#vip5
    //xmppURL: 'im-api-vip5.easemob.com',
    //apiURL: (location.protocol === 'https:' ? 'https:' : 'http:') + '//a1-vip5.easemob.com',
    //账号：10vip5/123456 ,11vip5/123456
    options = {
        xmppURL: 'im-api-vip5.easemob.com',
        apiUrl: (location.protocol === 'https:' ? 'https:' : 'http:') + '//a1-vip5.easemob.com',
        appKey: "1142170822178398#hys-huicy",
        user: "huicy",
        pwd: "123456"
    };
    conn.open(options)

}
//=============================================================================
/**
 * Created by clock on 16-11-30.
 */
window.onload = function () {
document.getElementById('register').onclick = register;
document.getElementById('privateText').onclick = sendPrivateText;
document.getElementById('privateCmd').onclick = sendPrivateCmd;
document.getElementById('privateImg').onclick = sendPrivateImg;
document.getElementById('getBlackList').onclick = getBlackList;
document.getElementById('addToBlackList').onclick = addToBlackList;
document.getElementById('removeFromBlackList').onclick = removeBlackList;

document.getElementById('roomText').onclick = sendRoomText;
document.getElementById('roomJoin').onclick = joinRoom;
document.getElementById('roomQuit').onclick = quitRoom;
document.getElementById('listChatRooms').onclick = listRooms;

document.getElementById('groupText').onclick = sendGroupText;
document.getElementById('groupMember').onclick = queryRoomMember;
document.getElementById('groupCreate').onclick = createGroup;
document.getElementById('queryGroupInfo').onclick = queryGroupInfo;
document.getElementById('leaveGroup').onclick = leaveGroup;
document.getElementById('addToGroupBlackList').onclick = addToGroupBlackList;
document.getElementById('listGroups').onclick = listGroups;
document.getElementById('changeGroupInfo').onclick = changeGroupInfo;
document.getElementById('getGroupBlackList').onclick = getGroupBlackList;
document.getElementById('destroyGroup').onclick = destroyGroup;
document.getElementById('addGroupMembers').onclick = addGroupMembers;
document.getElementById('removeGroupMemberFromBlackList').onclick = removeFromGroupBlackList;

document.getElementById('getRoasters').onclick = getRoasters;
document.getElementById('addFriends').onclick = addFriends;
document.getElementById('removeRoster').onclick = removeFriends;

document.getElementById('rtCall').onclick = call;
document.getElementById('rtEndCall').onclick = endCall;
document.getElementById('rtAcceptCall').onclick = acceptCall;
document.getElementById('rtAudioCall').onclick = audioCall;

document.getElementById('logout').onclick = logout;
document.getElementById('reConn').onclick = reConn;

// 贴图发送（放到消息模块里）
document.addEventListener('paste', function (e) {
    if (e.clipboardData && e.clipboardData.types) {
        if (e.clipboardData.items.length > 0) {
            if (/^image\/\w+$/.test(e.clipboardData.items[0].type)) {
                var blob = e.clipboardData.items[0].getAsFile();
                var url = window.URL.createObjectURL(blob);
                var id = conn.getUniqueId();//生成本地消息id

                var msg = new WebIM.message('img', id);
                msg.set({
                    apiUrl: WebIM.config.apiURL,
                    file: {data: blob, url: url},
                    to: 'asdfghj',
                    roomType: false,
                    chatType: 'singleChat',
                    onFileUploadError: function (error) {
                        console.log("Error");
                    },
                    onFileUploadComplete: function (data) {
                        console.log("Complete", data);
                    },
                    success: function (id) {
                        console.log("Success");
                    }
                });
                conn.send(msg.body);
            }
        }
    }
});

options = {
    apiUrl: WebIM.config.apiURL,
    user: "zzf2",
    pwd: "z",
        appKey: WebIM.config.appkey
    };
    conn.open(options);
};

