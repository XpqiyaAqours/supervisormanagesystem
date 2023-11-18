package com.smsys.system.service.impl;

import com.smsys.system.entity.Publication;
import com.smsys.system.mapper.PublicationMapper;
import com.smsys.system.service.IPublicationService;
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
public class PublicationServiceImpl extends ServiceImpl<PublicationMapper, Publication> implements IPublicationService {

    @Override
    public Publication getByResultId(Integer id) {
        Publication publication = baseMapper.getByResultId(id);
        return publication;
    }
}
