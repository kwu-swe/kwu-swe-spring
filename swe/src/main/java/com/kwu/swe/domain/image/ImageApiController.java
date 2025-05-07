package com.kwu.swe.domain.image;

import com.kwu.swe.global.util.DataBucketUtil;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageApiController {

    private final DataBucketUtil dataBucketUtil;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponseDto<List<String>> convertFileToURL(@RequestPart List<MultipartFile> multipartFiles) {
        List<String> result = multipartFiles.stream().map(multipartFile ->
                        dataBucketUtil.appendPrefix(dataBucketUtil.uploadImage(multipartFile)))
                .toList();
        return ApiResponseDto.onSuccess(result);
    }
}
