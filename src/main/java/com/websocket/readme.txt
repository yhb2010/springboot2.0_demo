如果存在session：
	@ServerEndpoint(value = "/websocket/{sid}", configurator = GetHttpSessionConfigurator.class)
	public void onOpen(Session session, EndpointConfig config, @PathParam("sid") String sid) throws IOException{
		this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

访问地址：
	http://localhost:8083/socket.html
推送消息：
	http://localhost:8083/checkcenter/socket/push/20?message=111