package com.xgsama.apiuse.poi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

/**
 * WeeklyItem
 *
 * @author: xgsama
 * @date: 2022-04-23 19:17:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyItem implements Comparable<WeeklyItem> {
    private Long id;
    private String customer;
    private String desc;
    private String remarks;
    private Boolean isDifficulty;
    private String difficulty;
    private Integer status;
    private Integer createdBy;

    private LocalDateTime createdTime;
    private LocalDateTime finishedTime;

    @Override
    public int compareTo(@NotNull WeeklyItem o) {
        return -1;
    }
}
