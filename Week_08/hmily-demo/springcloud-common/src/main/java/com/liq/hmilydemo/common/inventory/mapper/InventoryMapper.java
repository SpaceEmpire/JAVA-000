package com.liq.hmilydemo.common.inventory.mapper;

import com.liq.hmilydemo.common.inventory.dto.InventoryDTO;

/**
 * @description Inventory mapper
 * author: liquan
 * date: 2020/12/09 22:25
 * version: 1.0
 */
public interface InventoryMapper {

    /**
     * 减库存
     * @param inventoryDTO 库存实体DTO
     * @return
     */
    int decrease(InventoryDTO inventoryDTO);

    /**
     * 确认减库存
     * @param inventoryDTO 库存实体DTO
     * @return
     */
    int confirm(InventoryDTO inventoryDTO);

    /**
     * 取消减库存
     * @param inventoryDTO 库存实体DTO
     * @return
     */
    int cancel(InventoryDTO inventoryDTO);
}
