package com.websocket;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.domain.ServiceResult;

/**消息推送
至于推送新信息，可以再自己的Controller写个方法调用WebSocketServer.sendInfo();即可
 * @author DELL
 *
 */
@Controller
@RequestMapping("/checkcenter")
public class CheckCenterController {

	//推送数据接口
	@ResponseBody
	@RequestMapping("/socket/push/{cid}")
	public ServiceResult<String> pushToWeb(@PathVariable String cid, String message) {
		try {
			WebSocketServer.sendInfo(message,cid);
		} catch (IOException e) {
			e.printStackTrace();
			return ServiceResult.getFailureResult(cid+"#"+e.getMessage());
		}
		return ServiceResult.getSuccessResult(cid);
	}

}
