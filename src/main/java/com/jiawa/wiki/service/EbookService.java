package com.jiawa.wiki.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.wiki.domain.Ebook;
import com.jiawa.wiki.domain.EbookExample;
import com.jiawa.wiki.mapper.EbookMapper;
import com.jiawa.wiki.req.EbookQueryReq;
import com.jiawa.wiki.req.EbookSaveReq;
import com.jiawa.wiki.resp.EbookResp;
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
public class EbookService {
    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<EbookResp> list(EbookQueryReq req){



        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%" + req.getName() + "%");
        }
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        LOG.info("总行数：{}",pageInfo.getTotal());
        LOG.info("总页数：{}",pageInfo.getPages());


//        List<EbookResp> respList =new ArrayList<>();
//        for (Ebook ebook : ebookList) {
//            EbookResp ebookResp = new EbookResp();
//            //ebookResp.setId(ebook.getId());
//            BeanUtils.copyProperties(ebook,ebookResp);
//            respList.add(ebookResp);
//        }
        List<EbookResp> list = CopyUtil.copyList(ebookList, EbookResp.class);

        PageResp<EbookResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    /**
     *
     * @param req
     * 保存
     */

    public void save(EbookSaveReq req){
        Ebook ebook = CopyUtil.copy(req,Ebook.class);
        if (ObjectUtils.isEmpty(req.getId())){
            //新增
            ebook.setId(snowFlake.nextId());
            ebookMapper.insert(ebook);
        }else{
            //更新
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }


    /**
     * 删除
     */
    public void delete(Long id){
        ebookMapper.deleteByPrimaryKey(id);
    }
}
