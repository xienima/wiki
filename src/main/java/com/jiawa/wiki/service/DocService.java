package com.jiawa.wiki.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.wiki.domain.Doc;
import com.jiawa.wiki.domain.DocExample;
import com.jiawa.wiki.mapper.DocMapper;
import com.jiawa.wiki.req.DocQueryReq;
import com.jiawa.wiki.req.DocSaveReq;
import com.jiawa.wiki.resp.PageResp;
import com.jiawa.wiki.util.CopyUtil;
import com.jiawa.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DocService {
    private static final Logger LOG = LoggerFactory.getLogger(DocService.class);

    @Resource
    private DocMapper docMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<DocResp> list(DocQueryReq req){



        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        DocExample.Criteria criteria = docExample.createCriteria();
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Doc> docList = docMapper.selectByExample(docExample);

        PageInfo<Doc> pageInfo = new PageInfo<>(docList);
        LOG.info("总行数：{}",pageInfo.getTotal());
        LOG.info("总页数：{}",pageInfo.getPages());


//        List<DocResp> respList =new ArrayList<>();
//        for (Doc doc : docList) {
//            DocResp docResp = new DocResp();
//            //docResp.setId(doc.getId());
//            BeanUtils.copyProperties(doc,docResp);
//            respList.add(docResp);
//        }
        List<DocResp> list = CopyUtil.copyList(docList, DocResp.class);

        PageResp<DocResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    /**
     *
     * @param req
     * 保存
     */

    public void save(DocSaveReq req){
        Doc doc = CopyUtil.copy(req,Doc.class);
        if (ObjectUtils.isEmpty(req.getId())){
            //新增
            doc.setId(snowFlake.nextId());
            docMapper.insert(doc);
        }else{
            //更新
            docMapper.updateByPrimaryKey(doc);
        }
    }


    /**
     * 删除
     */
    public void delete(Long id){
        docMapper.deleteByPrimaryKey(id);
    }

    public List<DocResp> all(){
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        List<Doc> docList = docMapper.selectByExample(docExample);

        List<DocResp> list = CopyUtil.copyList(docList, DocResp.class);

        return list;
    }


}
