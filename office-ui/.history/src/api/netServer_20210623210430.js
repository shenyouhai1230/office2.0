const net =require("net");

//创建连接
let socket = net.createConnection(8888,"127.0.0.1",()=>{
	console.log("已经成功连接到服务器了，可以通讯了");
})

socket.on("data",(data)=>{
	console.log(data.toString());
    socket.setEncoding('utf8');
	socket.write("1111111111111\n");
})