package com.jiawa.wiki.controller;

import com.jiawa.wiki.req.DocQueryReq;
import com.jiawa.wiki.req.DocSaveReq;
import com.jiawa.wiki.resp.CommonResp;
import com.jiawa.wiki.resp.DocResp;
import com.jiawa.wiki.resp.PageResp;
import com.jiawa.wiki.service.DocService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {

    @Resource
    private DocService docService;


    @GetMapping("/list")
    public CommonResp list(@Valid DocQueryReq req){
        CommonResp<PageResp<DocResp>> resp = new CommonResp<>();
        PageResp<DocResp> list =docService.list(req);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/all")
    public CommonResp all(){
        CommonResp<List<DocResp>> resp = new CommonResp<>();
        List<DocResp> list =docService.all();
        resp.setContent(list);
        return resp;
    }


    @PostMapping("/save")
    public CommonResp save(@ Valid @RequestBody DocSaveReq req){
        CommonResp resp = new CommonResp<>();
        docService.save(req);
        return resp;
    }

    @DeleteMapping("/detele/{idsStr}")
    public CommonResp save(@PathVariable String idsStr ){
        CommonResp resp = new CommonResp<>();
        //将String转成List String->array->List
        List<String> list  =Arrays.asList(idsStr.split(","));
        docService.delete(list);
        return resp;
    }


    @GetMapping("/find-content/{id}")
    public CommonResp findContent(@Valid Long id){
        CommonResp<String> resp = new CommonResp<>();
        String content =docService.findContent(id);
        resp.setContent(content);
        return resp;
    }
}
