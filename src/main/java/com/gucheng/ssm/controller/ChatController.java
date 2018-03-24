package com.gucheng.ssm.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gucheng.ssm.model.User;
import com.gucheng.ssm.service.UserService;
import com.gucheng.ssm.util.UploadUtil;


@Controller
@RequestMapping("/")
@SessionAttributes("userid")
public class ChatController {
	
    private User user;
    @Resource 
    private UserService userService;
	
	/**
     * 聊天主页
     */
    @RequestMapping(value = "chat.do")
    public ModelAndView getIndex(){
        ModelAndView view = new ModelAndView("index");
        return view;
    }
    
    /**
     * 显示个人信息页面
     */
    @RequestMapping(value = "{userid}", method = RequestMethod.GET)
    public ModelAndView selectUserByUserid(
    		@PathVariable("userid") String userid, 
    		@ModelAttribute("userid") String sessionid){
        ModelAndView view = new ModelAndView("information");
        user = userService.selectUserByUserid(userid);
        view.addObject("user", user);
        return view;
    }
    
    /**
     * 显示个人信息编辑页面
     * @param userid
     * @param sessionid
     * @return
     */
    @RequestMapping(value = "{userid}/config.do")
    public ModelAndView setting(@PathVariable("userid") String userid, @ModelAttribute("userid") String sessionid){
        ModelAndView view = new ModelAndView("info-setting");
        user = userService.selectUserByUserid(userid);
        view.addObject("user", user);
        return view;
    }
    
    /**
     * 更新用户信息
     * @param userid
     * @param sessionid
     * @param user
     * @return
     */
    @RequestMapping(value = "{userid}/update.do", method = RequestMethod.POST)
    public String  update(@PathVariable("userid") String userid, @ModelAttribute("userid") String sessionid,
    		User user, RedirectAttributes attributes,HttpServletRequest request){
		try {
			userService.update(user);
			attributes.addFlashAttribute("message", "[" + userid + "]资料更新成功!");
		} catch (Exception e) {
			attributes.addFlashAttribute("error", "[" + userid + "]资料更新失败!");
		}
        return "redirect:/{userid}/config.do";
    }
    
    /**
     * 头像上传
     * @param userid
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "{userid}/upload.do",method=RequestMethod.POST)
    public String upload(@PathVariable("userid") String userid, MultipartFile file, HttpServletRequest request, UploadUtil uploadUtil,
                         RedirectAttributes attributes){
        try{
            String fileurl = uploadUtil.upload(request, "upload", userid);
            user = userService.selectUserByUserid(userid);
            user.setProfilehead(fileurl);
            boolean flag = userService.update(user);
            if(flag){
                attributes.addFlashAttribute("message", "["+userid+"]头像更新成功!");
            }else{
                attributes.addFlashAttribute("error", "["+userid+"]头像更新失败!");
            }
        } catch (Exception e){
        	e.printStackTrace();
            attributes.addFlashAttribute("error", "["+userid+"]头像更新失败!");
        }
        return "redirect:/{userid}/config.do";
    }
    
    /**
     * 获取用户头像
     * @param userid
     */
    @RequestMapping(value = "{userid}/head.do")
    public void head(@PathVariable("userid") String userid, HttpServletRequest request, HttpServletResponse response){
        try {
            user = userService.selectUserByUserid(userid);
            String path = user.getProfilehead();
            String rootPath = request.getSession().getServletContext().getRealPath("/");
            String picturePath = rootPath + path;
            response.setContentType("image/jpeg; charset=UTF-8");
            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(picturePath);
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            outputStream = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 修改密码
     * @param userid
     * @param oldpass
     * @param newpass
     * @return
     */
    @RequestMapping(value = "{userid}/pass.do", method = RequestMethod.POST)
    public String changePassword(@PathVariable("userid") String userid, String oldpass, String newpass, RedirectAttributes attributes,
                                 HttpServletRequest request){
        user = userService.selectUserByUserid(userid);
        if(oldpass.equals(user.getPassWord())){
            user.setPassWord(newpass);
            boolean flag = userService.update(user);
            if(flag){
                attributes.addFlashAttribute("message", "["+userid+"]密码更新成功!");
            }else{
                attributes.addFlashAttribute("error", "["+userid+"]密码更新失败!");
            }
        }else{
            attributes.addFlashAttribute("error", "密码错误!");
        }
        return "redirect:/{userid}/config.do";
    }
    
    @RequestMapping(value = "")
    public String index() {
        return "redirect:/user/login.do";
    }

    @RequestMapping(value = "/about.do")
    public String about() {
        return "about";
    }

    @RequestMapping(value = "/help.do")
    public String help() {
        return "help";
    }

    @RequestMapping(value = "/system.do")
    public String system() {
        return "system-setting";
    }


}
