package com.zwyue.controller.archive;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.common.ResultUtils;
import com.zwyue.common.TimeUtil;
import com.zwyue.entity.Memorabilia;
import com.zwyue.service.MemorabiliaService;
import com.zwyue.util.DocUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 档案管理
 *
 * @author zwy
 * @date 2018/11/28 14:08
 */
@RestController
@RequestMapping("memorabilia")
public class MemorabiliaController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MemorabiliaController.class);

    @Autowired
    private MemorabiliaService memorabiliaService ;

    /**
     * 新增档案
     *
     * @author zwy
     * @date 2018/11/28 14:08
     */
    @RequestMapping(value = "save" ,method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> saveMemorabilia(Memorabilia memorabilia,HttpSession httpSession){
        logger.info("========保存档案=======");
        /*Teacher teacher = getLoginUser(httpSession);
        memorabilia.setUserid(teacher.getId());
        memorabilia.setUsername(teacher.getTname());*/
        memorabiliaService.saveMemorabilia(memorabilia);
        //前台Ajax需要返回数据为json，否则不会进入success函数
        return ResultUtils.success("保存成功");
    }

    /**
     * 档案删除
     *
     * @author zwy
     * @date 2018/11/28 14:09
     */
    @CrossOrigin
    @RequestMapping(value = "delete")
    /*public Map deleteMemorabilia(@RequestParam(value = "idList[]") List<Integer> idList){
        logger.info("======删除{}条档案======",idList.size());
        Integer delete = memorabiliaService.deleteMemorabilia(idList);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else {
            return ResultUtils.error("删除成功");
        }
    }*/
    public Map delete(Integer id){
        Integer delete = memorabiliaService.deleteByPrimaryKey(id);
        if(delete>0){
            return ResultUtils.success("删除失败");
        }else {
            return ResultUtils.error("删除成功");
        }
    }

    /**
     * 档案更新
     *
     * @author zwy
     * @date 2018/11/28 14:09
     */
    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public Map updateMemorabilia(Memorabilia memorabilia){
        logger.info("=========档案更新=========");
        memorabiliaService.updateMemorabilia(memorabilia);
        return ResultUtils.success("更新成功") ;
    }

    /**
     * 档案查询
     *
     * @author zwy
     * @date 2018/11/28 14:09
     */
    @CrossOrigin
    @RequestMapping(value = "queryMemorabiliaById" ,method = RequestMethod.GET)
    public Map queryMemorabilia(Integer id){
        logger.info("========查询id为{}的档案========",id);
        Memorabilia memorabilia = memorabiliaService.queryMemorabiliaById(id);
        return ResultUtils.success(memorabilia);
    }

    /**
     * 档案列表
     *
     * @author zwy
     * @date 2018/11/28 14:10
     */
    @CrossOrigin
    @RequestMapping(value = "list" ,method = RequestMethod.GET)
    public Map<String, Object> toDetail(Memorabilia memorabilia){
        logger.info("========查询档案列表========");
        PageInfo<Memorabilia> pageInfo = new PageInfo<>(memorabiliaService.queryAllMemorabilia(memorabilia));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 根据标题查询大事记列表
     * @author yangli
     * @date 2019/1/3
     * @param
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "getTitle")
    public Map getTitle(String title){
        PageInfo<Memorabilia> pageInfo = new PageInfo<>(memorabiliaService.getTitleList(title));
        return ResultUtils.success(pageInfo);
    }

    /**
     * 导出大事记并下载word文档
     * @author yangli
     * @date 2019/1/9
     */
    @CrossOrigin
    @RequestMapping(value = "export")
    public void getDoc(HttpServletRequest request, HttpServletResponse response, Integer id){
        String names="memorabilia.xml";
        Memorabilia memorabilia=memorabiliaService.queryMemorabiliaById(id);
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("time", TimeUtil.getUserDate(memorabilia.getCrttime()));
        dataMap.put("content", memorabilia.getContent());
        String newWordName = "大事记记录.doc";
        //调用打印word的函数
        DocUtil.download(request, response, newWordName, dataMap,names);
    }
}
