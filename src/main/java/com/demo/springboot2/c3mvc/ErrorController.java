package com.demo.springboot2.c3mvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdel.util.helper.JacksonUtil;

/**在springboot中，Controller中的异常默认都交给/error处理
 * 提供了多种从request里获取错误信息：
 * 1、timestamp错误发生的时间
 * 2、status：对于与http status
 * 3、error：错误消息，如bad request
 * 4、message：详细错误信息
 * 5、exception：如果应用抛出异常，exception是字符串，代表异常的类名
 * 6、path：请求的url
 * 7、errors：@Validated校验错误的时候，校验结果信息放到这里
 * @author DELL
 *
 */
@Controller
public class ErrorController extends AbstractErrorController{

	public ErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@RequestMapping("/error")
	//考虑到异常信息直接显示在页面上对用户不合适，尤其是RuntimeException，还要区分页面渲染和json请求这两种不同的情况，前者应返回错误页面，后者应返回json结果
	public ModelAndView getErrorPath(HttpServletRequest request, HttpServletResponse response) {
		//getErrorAttributes是AbstractErrorController提供的用于获取错误信息的方法，返回一个map
		Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(request, false));
		//获取异常，有可能为空
		Throwable cause = getCause(request);
		Integer status = (Integer)model.get("status");
		//获取错误信息
		String message = (String)model.get("message");
		//友好提示
		String errorMessage = getErrorMessage(cause);
		//后台打印日志信息方便查错
		System.out.println(status + ", " + message + ", " + cause);
		response.setStatus(status);
		if(!isJsonRequest(request)){
			//error.btl模板显示错误的详细信息
			ModelAndView view = new ModelAndView("/error.btl");
			view.addAllObjects(model);
			view.addObject("errorMessage", errorMessage);
			view.addObject("status", status);
			view.addObject("cause", cause);
			return view;
		}else{
			Map error = new HashMap();
			error.put("success", false);
			error.put("errorMessage", errorMessage);
			error.put("message", message);
			writeJson(response, error);
			return null;
		}
	}

	private void writeJson(HttpServletResponse response, Map error) {
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf8");
            PrintWriter pw=response.getWriter();
            String str=JacksonUtil.ObjecttoJSon(error);
            pw.print(str);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	//用来区分客户端发起的是页面渲染请求还是json请求
	private boolean isJsonRequest(HttpServletRequest request) {
		String requestUri = (String)request.getAttribute("javax.servlet.error.request_uri");
		if(requestUri != null && requestUri.endsWith(".json")){
			return true;
		}else{
			//也可以通过获取http头，根据accept字段是否包含json来进一步判断，比如
			if(request.getContentType() == null){
				return true;
			}
			return request.getContentType().contains("application/json");
		}
	}

	//返回一个友好的异常信息，而不是spring boot提供的message包含的信息
	private String getErrorMessage(Throwable cause) {
		return "服务器错误，请与管理员联系";
	}

	//用于获取应用的系统异常
	private Throwable getCause(HttpServletRequest request) {
		Throwable error = (Throwable)request.getAttribute("javax.servlet.error.exception");
		if(error != null){
			//mvc有可能会封装异常成ServletException，需要调用getCause获取真正的异常
			while(error instanceof ServletException && error.getCause() != null){
				error = ((ServletException)error).getCause();
			}
		}
		return error;
	}

	@Override
	public String getErrorPath() {
		return null;
	}

}
