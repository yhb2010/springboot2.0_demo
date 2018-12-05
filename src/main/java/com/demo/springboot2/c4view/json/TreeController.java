package com.demo.springboot2.c4view.json;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdel.util.helper.JacksonUtil;
import com.demo.springboot2.domain.User;
import com.demo.springboot2.domain.UserJson;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TreeController {

	@Autowired
	private ObjectMapper mapper;

	//Jackson提供了通过树的方式遍历json对象，这很适合没有pojo对应的json
	//JsonNode提供了asXXX方法读取对应的值
	//isArray判断JsonNode是否是数组，如果是，可以调用get(i)进行遍历，通过size()获取长度
	//get(String)，获取当前节点的子节点，返回JsonNode
	@GetMapping("/tree")
	public String tree() throws IOException{
		String json = "{\"name\":\"lijz\", \"id\":10}";
		JsonNode node = mapper.readTree(json);
		String name = node.get("name").asText();
		int id = node.get("id").asInt();
		return "name:"+name+",id:"+id;
	}

	//如果有pojo，直接调用readValue和writeValueAsString
	@GetMapping("/pojo")
	public String pojo() throws Exception{
		String json = "{\"name\":\"lijz\", \"id\":10}";
		User user = mapper.readValue(json, User.class);
		return "name:"+user.getName()+",id:"+user.getId();
		//User u = new User(12, "aa");
		//String str = mapper.writeValueAsString(u);
	}

	//流式操作
	//JsonParser的解析结果包含了一系列JsonToken，JsonToken是一个枚举，常用的START_OBJECT代表符号"{"，START_ARRAY和END_ARRAY代表"["，"]"，
	//FIELD_NAME表示一个json key，VALUE_STRING代表一个json value，字符串类型。VALUE_NUMBER_INT表示一个整数类型。
	@GetMapping("/stream")
	public String stream() throws Exception{
		String json = "{\"name\":\"lijz\", \"id\":10}";
		JsonFactory f = mapper.getFactory();
		String key = null;
		Object value = null;
		JsonParser parser = f.createParser(json);
		//{，START_OBJECT，忽略第一个token
		JsonToken token = parser.nextToken();
		String result = "";
		while((token = parser.nextToken()) != null){
			//"name", FIELD_NAME
			if(token == JsonToken.FIELD_NAME){
				key = parser.getCurrentName();
				result+= key + "=";
			}
			//"lijz", VALUE_STRING
			if(token == JsonToken.VALUE_STRING){
				value = parser.getValueAsString();
				result+= value + ",";
			}
			if(token == JsonToken.VALUE_NUMBER_INT){
				value = parser.getValueAsInt();
				result+= value + ",";
			}
		}
		parser.close();
		return result;
	}

	@GetMapping("/other/json")
	public String otherJson(){
		UserJson u = new UserJson();
		u.setId(1);
		u.setName("zsl");
		u.setCreateTime(new Date());
		u.getMap().put("newAttr", "11111");
		u.getMap().put("newAttr2", "2222");
		String str = JacksonUtil.ObjecttoJSon(u);
		return str;
	}

	@GetMapping("/other/json2")
	public String otherJson2(){
		String json = "{\"id\":1,\"userName\":\"zsl\",\"newAttr2\":\"2222\",\"newAttr\":\"11111\"}";
		UserJson u = JacksonUtil.jsonToObject(json, UserJson.class);
		return u.getName();
	}

	@JsonView(UserJson.IdView.class)
	@GetMapping("/json/view")
	public UserJson jsonView(){
		UserJson u = new UserJson();
		u.setId(1);
		u.setName("zsl");
		return u;
	}

	@JsonView(UserJson.IdNameView.class)
	@GetMapping("/json/view2")
	public UserJson jsonView2(){
		UserJson u = new UserJson();
		u.setId(1);
		u.setName("zsl");
		return u;
	}

}
