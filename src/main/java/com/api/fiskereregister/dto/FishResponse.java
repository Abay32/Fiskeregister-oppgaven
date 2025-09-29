package com.api.fiskereregister.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FishResponse {
    private List<FishDto> fishList;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int  totalPages;
    private boolean last;
}
