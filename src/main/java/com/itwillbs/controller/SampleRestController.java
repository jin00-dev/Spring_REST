package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.SampleVO;

@RestController
@RequestMapping(value = "/sample")
public class SampleRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleRestController.class);
	
	// http://localhost:8088/sample/msg
	@RequestMapping(value = "/msg" , method = RequestMethod.GET)
	public String testTEXT() throws Exception{
		logger.debug("testTEXT() 호출");
		return "ITWILL"; 
	}
	
	// http://localhost:8088/sample/msg2
	@RequestMapping(value = "/msg2" , method = RequestMethod.GET)
	public Integer testNUMBER() throws Exception{
		logger.debug("testNUMBER() 호출");
		return 1000; 
	}
	
	// http://localhost:8088/sample/msg3
	@RequestMapping(value = "/msg3" , method = RequestMethod.GET)
	public @ResponseBody SampleVO testObject() throws Exception{
		logger.debug("testObject() 호출");
		
		SampleVO vo = new SampleVO();
		vo.setSno(1);
		vo.setName("관리자");
		vo.setEmail("itwill@admin.com");
		
		return vo; 
	}
	
	// http://localhost:8088/sample/msg4
	@RequestMapping(value = "/msg4" , method = RequestMethod.GET)
	public List<SampleVO> testList() throws Exception{
		logger.debug("testList() 호출");
		
		List<SampleVO> sampleList = new ArrayList<SampleVO>();
		
		for(int i=0; i<10; i++) {
		
			SampleVO vo = new SampleVO();
			vo.setSno(i);
			vo.setName("관리자"+i);
			vo.setEmail("itwill"+i+"@admin.com");
			
			sampleList.add(vo);
		}
		// List(ArrayList) => JSON Array로 변경
		return sampleList; 
	}
	
	// http://localhost:8088/sample/msg5
	@RequestMapping(value = "/msg5" , method = RequestMethod.GET)
	public Map<Integer,SampleVO> testMap() throws Exception{
		logger.debug("testMap() 호출");
		
		Map<Integer, SampleVO> sampleMap = new HashMap<Integer, SampleVO>();
		
		for(int i=0; i<10; i++) {
			
			SampleVO vo = new SampleVO();
			vo.setSno(i);
			vo.setName("관리자"+i);
			vo.setEmail("itwill"+i+"@admin.com");
			
			sampleMap.put(i, vo);
		}
		return sampleMap; 
	}
	
	// http://localhost:8088/sample/msg6 (x)
	// http://localhost:8088/sample/msg?num=6 (x) ?num=6 을 통해 연결된 view에 출력하겠다. 
	// 위아래 차이 => 위는 뷰에서 처리 (일반 컨트롤러) 아래는 거의 REST 방식의 처리(데이터)
	// http://localhost:8088/sample/msg/6 (o) /6을 통해 데이터 값을 만든다 + 뷰페이지 없음, 데이터만 존재
	 // 6은 파라메터가 아니다 파라메터는 ~~?num=값 형태..!! 
	@RequestMapping(value = "/msg/{num}", method = RequestMethod.GET)
	public int testInt(@PathVariable("num") int num) throws Exception{
		logger.debug("testInt() 실행");
		logger.debug("num : " + num );
		
		return 1000;
	}
	
	// data : {sno:10,name:"사용자",email:"email@email.com"}, 파라메터로 받았어  
	//http://localhost:8088/sample/testAjax
	@RequestMapping(value = "/testAjax", method = RequestMethod.POST)
	public String testAjax(@RequestBody SampleVO vo) {
		
		//@RequestBody => 브라우저에서 전달되는 JSON 타입의 데이터를 자동으로
		//                해당 타입의 객체로 전환 하는 기능 
		
		logger.debug("testAjax() - POST 방식 호출");
		logger.debug("vo : " + vo);
		
		return "OK";
	}
	
	
	@RequestMapping(value = "/testAjax2", method = RequestMethod.POST)
	public SampleVO testAjax2(@RequestBody SampleVO vo) {
		
		
		logger.debug("testAjax2() - POST 방식 호출");
		logger.debug("vo : " + vo);

		SampleVO resultVO = vo; 
		resultVO.setName("어쩌고 저쩌고");
		
		return resultVO;
	}
	
	//http://localhost:8088/sample/resp
	@RequestMapping(value = "/resp", method = RequestMethod.GET)
	public ResponseEntity<String> testRespEntity() throws Exception {
		logger.debug("testRespEntity() 호출");
		
//		return "ITWILL BUSAN"; 
//		return new ResponseEntity<String>("ITWILL BUSAN",HttpStatus.NOT_FOUND); //그냥 404상태 보낸것  
//		return new ResponseEntity<String>("ITWILL BUSAN",HttpStatus.BAD_REQUEST); //내가 강제로 상태부여 한 것 
																				// 보는 사람이 상태 보고 처리 하려고 
		// 실제로는 어떻게 쓰냐면요 => 뷰에서 확인 할 수 없어서!! 
		// 데이터와 상태를 같이 줄 수 있는 객체 : ResponseEntity
		ResponseEntity<String> entity = null; 
		if(true) {
			entity = new ResponseEntity<String>("ITWILL BUSAN",HttpStatus.OK);
		}else {
			entity = new ResponseEntity<String>("ITWILL BUSAN",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
		
	}
	
	//http://localhost:8088/sample/resp2
	// 객체 담아서 사용 할 때! (주로 사용) + list => 일반적인 형태 
	@RequestMapping(value = "/resp2", method = RequestMethod.GET)
	public ResponseEntity<List<SampleVO>> testRespEntity2() throws Exception {
		logger.debug("testRespEntity2() 호출");
		// 제네릭 = 이 객체(List)를 만들 때 이것(SampleVO)만 저장하게 하겠다. / List<List<String>> 이라는 형태도 있음
		
		/*
		 * ResponseEntity<SampleVO> entity = null; if(true) { entity = new
		 * ResponseEntity<SampleVO>(Obj,HttpStatus.OK); }else { entity = new
		 * ResponseEntity<SampleVO>(Obj,HttpStatus.INTERNAL_SERVER_ERROR); }
		 */
		List<SampleVO> arr = new ArrayList<SampleVO>();
		
		return new ResponseEntity<List<SampleVO>>(arr, HttpStatus.OK);
	}
	
	
	
	//http://localhost:8088/sample/resp3
	// 이런 형태의 코드도 가능해요 
	@RequestMapping(value = "/resp3", method = RequestMethod.GET)
	public ResponseEntity<String> testRespEntity3() throws Exception {
		logger.debug("testRespEntity3() 호출");
		
		HttpHeaders respHeader = new HttpHeaders();
		respHeader.add("Content-Type", "text/html; charset=UTF-8"); //쓸수 있다! 정도. 
		
		String data = "<script>";
		data += "alert('처리결과 : ㅇㅇㅇ');";
		data += "</script>";
		
		return new ResponseEntity<String>(data,respHeader, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
}//SampleRestController

