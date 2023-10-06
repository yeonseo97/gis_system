package com.gis.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gis.dto.member.MemberDto;
import com.gis.service.member.MemberServiceImpl;
import com.gis.util.member.PasswordEncoderUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor // 생성자 만들어줌 
@Controller
public class MemberController {
	
	private final MemberServiceImpl memberService;
	
	/**
	 * 메인 페이지(회원 전용)
	 * @author 임연서
	 */
	@GetMapping("/")
	public String memberPage(HttpServletRequest request) {
		// 세션에서 사용자 정보 가져오기
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");

        // 사용자가 로그인되어 있지 않으면 로그인 페이지로 리다이렉트
        if (member == null) {
        	log.info("회원이 아님");
            return "redirect:/login";
        }
        // 로그인된 사용자만 접근 가능한 페이지
        log.info("세션: {}", session);
		return "member/member";
	}

	/**
	 * 로그인 화면으로 이동
	 * @author 임연서
	 */
	@GetMapping("/login")
	public String loginPage() {
		return "member/login";
	}
	
	/**
	 * 로그인
	 * @author 임연서
	 */
    @PostMapping("/login")
    public String loginProcess(
    		@RequestParam String loginId,
    		@RequestParam String password, 
    		HttpSession session,
    		RedirectAttributes attributes) {
        MemberDto memberDTO = memberService.findByLoginId(loginId);
    	log.info("loginId : {}", loginId);
        if (memberDTO != null) {
            // 사용자의 솔트 값을 가져옴
            String salt = memberDTO.getSalt();
            log.info("가져온 솔트 값: {}", salt);
            // 사용자가 입력한 비밀번호를 해싱하여 저장된 해시와 비교
            String hashedPassword = PasswordEncoderUtil.hashPassword(password, salt);

            if (memberDTO.getPassword().equals(hashedPassword)) {
                session.setAttribute("member", memberDTO);
                return "redirect:/"; // 로그인 성공 시 메인 페이지로 이동
            }
        } 
        attributes.addFlashAttribute("error", "* 아이디 또는 비밀번호가 일치하지 않습니다.");
        return "redirect:/login"; // 로그인 실패 시 다시 로그인 페이지로 이동
    }
	
	/**
	 * 로그아웃
	 * @author 임연서
	 */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 세션에서 사용자 정보 삭제
        request.getSession().invalidate();
        return "redirect:/login"; // 로그아웃 후 로그인 페이지로 이동
    }
    
    
	/**
	 * 회원가입 화면으로 이동
	 * @author 임연서
	 */
	@GetMapping("/signup")
	public String signupPage() {
		return "member/signup";
	}
	
	/**
	 * 회원가입
	 * @author 임연서
	 */
	@PostMapping("/signup")
	public String singupProcess(
            RedirectAttributes attributes,
            @ModelAttribute("member") MemberDto member) {
		// 중복되는 아이디 확인
		if(memberService.existMember(member.getLoginId()) == false) {
			// 솔트 생성
	        String salt = PasswordEncoderUtil.generateRandomSalt();
	        
			// 비밀번호 암호화
	        String hashedPassword = PasswordEncoderUtil.hashPassword(member.getPassword(), salt);
	        // 해시된 비밀번호와 솔트 저장
	        member.setPassword(hashedPassword);
	        member.setSalt(salt);
			// 가입 처리 
			memberService.insertMember(member);
			
		} else {
			attributes.addFlashAttribute("error", "* 이미 사용 중인 아이디입니다.");
			return "redirect:/signup";
		}
		return "redirect:/login";
	}
}