package com.yupi.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.project.common.ErrorCode;
import com.yupi.project.exception.BusinessException;

import com.yupi.project.mapper.InterfaceInfoMapper;
import com.yupi.project.model.entity.InterfaceInfo;
import com.yupi.project.service.InterfaceInfoService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【interface_info】的数据库操作Service实现
* @createDate 2024-02-26 19:42:29
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {
    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {

     String name = interfaceInfo.getName();
     String url = interfaceInfo.getUrl();
     String description = interfaceInfo.getDescription();
     String requestHeader = interfaceInfo.getRequestHeader();
     String responseHeader = interfaceInfo.getResponseHeader();
     String method = interfaceInfo.getMethod();


        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name, url, description, requestHeader, responseHeader, method)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }

        if (name != null && name.length() > 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口名称太长");
        }
        if (url != null && url.length() > 100) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口路径太长");
        }
        if (description != null && description.length() > 200) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口描述太长");
        }
        if (StringUtils.isNotBlank(requestHeader) && requestHeader.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口请求头太长");
        }
        if (StringUtils.isNotBlank(responseHeader) && responseHeader.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口响应头太长");
        }
    }
}




