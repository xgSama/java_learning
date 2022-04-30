package com.xgsama.apiuse.poi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Weekly
 *
 * @author: xgsama
 * @date: 2022-04-23 19:14:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weekly {
    private LocalDate startDate;
    private LocalDate endDate;

    @RowKey(key = "customer")
    private List<WeeklyItem> current;
    private List<WeeklyItem> next;
    private List<WeeklyItem> diff;

    public Map<String, List<WeeklyItem>> get() {
        return current.stream().collect(Collectors.groupingBy(WeeklyItem::getCustomer));

    }


    public LocalDate getStartDate() {
        return startDate;
    }
}
