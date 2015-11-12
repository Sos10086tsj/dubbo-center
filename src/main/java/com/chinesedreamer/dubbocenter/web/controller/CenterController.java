package com.chinesedreamer.dubbocenter.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinesedreamer.dubbocenter.job.constant.JobStatus;
import com.chinesedreamer.dubbocenter.job.model.Job;
import com.chinesedreamer.dubbocenter.job.service.JobService;
import com.chinesedreamer.dubbocenter.web.message.ResponseVo;
import com.chinesedreamer.dubbocenter.web.vo.JobVo;

@Controller
@RequestMapping(value = "mgmt")
public class CenterController {
	
	@Resource
	private JobService jobService;
	
	/**
	 * 跳转列表页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request){
		return "mgmt/list";
	}
	
	/**
	 * grid
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listStore", method = RequestMethod.GET)
	public List<JobVo> listStore(Model model, HttpServletRequest request){
		List<Job> jobs = this.jobService.getList(null);
		List<JobVo> vos = new ArrayList<JobVo>();
		for (Job job : jobs) {
			vos.add(JobVo.covert2Vo(job));
		}
		return vos;
	}
	
	/**
	 * 添加job
	 * @param request
	 * @param job
	
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseVo add(HttpServletRequest request, Job job){
		System.out.println("------------------update");
		System.out.println("job:" + job);
		return null;
	} */
	
	/**
	 * 更新job
	 * @param request
	 * @param job
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseVo update(HttpServletRequest request, Job job){
		if (null == this.jobService.getJob(job.getJobId())) {
			job.setStatus(JobStatus.STOP.getValue());
			this.jobService.addJob(job);
		}else {
			this.jobService.updateJob(job);
		}
		return ResponseVo.getSuccessResponse();
	}
	
	/**
	 * 删除job
	 * @param request
	 * @param job
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public ResponseVo delete(HttpServletRequest request, String[] jobIds){
		this.jobService.deleteJobInBatch(jobIds);
		return ResponseVo.getSuccessResponse();
	}
	
	/**
	 * 启用job
	 * @param request
	 * @param job
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "start", method = RequestMethod.POST)
	public ResponseVo start(HttpServletRequest request, String jobId){
		this.jobService.startJob(jobId);
		return ResponseVo.getSuccessResponse();
	}
	
	/**
	 * 停用job
	 * @param request
	 * @param job
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "stop", method = RequestMethod.POST)
	public ResponseVo stop(HttpServletRequest request, String jobId){
		this.jobService.stopJob(jobId);
		return ResponseVo.getSuccessResponse();
	}
}
