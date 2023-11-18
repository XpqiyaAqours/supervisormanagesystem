package com.smsys.system.service.impl;

import com.smsys.system.entity.Paper;
import com.smsys.system.mapper.PaperMapper;
import com.smsys.system.service.IPaperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements IPaperService {

    @Override
    public Paper getByResultId(Integer id) {
        Paper paper = baseMapper.getByResultId(id);
        return paper;
    }
}
