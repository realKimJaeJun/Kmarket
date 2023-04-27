package kr.co.Kmarket.controller.admin.cs.faq;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import kr.co.Kmarket.service.cs.CsFaqService;
import kr.co.Kmarket.service.cs.CsService;
import kr.co.Kmarket.vo.cs.CsCate1VO;
import kr.co.Kmarket.vo.cs.CsFaqVO;

@WebServlet("/admin/cs/faq/write.do")
public class WriteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CsFaqService service = CsFaqService.INSTANCE;
	private CsService service2 = CsService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 들어오는 값 없음
		// 카테고리1 값, 이름 가져오기 => 카테고리2는 동적 처리
		List<CsCate1VO> vos2 = service2.selectCsCate1();
		
		// 전송
		req.setAttribute("vos2", vos2);
		
		req.getRequestDispatcher("/admin/cs/faq/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 들어오는값 받기
		CsFaqVO vo = service.insertCsFaqVO(req);
		
		// 데이터 베이스 등록
		int result = service.insertCsFaq(vo);
		
		// 결과값 리턴
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().write(json.toString());
	}
}
