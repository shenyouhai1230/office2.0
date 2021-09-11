const net =require("net");

//创建连接
let socket = net.createConnection(8888,"192.0.0.10",()=>{
	console.log("已经成功连接到服务器了，可以通讯了");
})

socket.on("data",(data)=>{
	console.log(data.toString());
	socket.write("兔子哪里跑");
})
————————————————
版权声明：本文为CSDN博主「twozi118」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/weixin_44987155/article/details/89607430