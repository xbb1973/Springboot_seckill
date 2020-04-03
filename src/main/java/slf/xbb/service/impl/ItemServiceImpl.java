package slf.xbb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import slf.xbb.dao.ItemDoMapper;
import slf.xbb.dao.ItemStockDoMapper;
import slf.xbb.domain.ItemDo;
import slf.xbb.domain.ItemStockDo;
import slf.xbb.error.BussinessException;
import slf.xbb.error.EmBusinessError;
import slf.xbb.service.ItemService;
import slf.xbb.service.model.ItemModel;
import slf.xbb.validator.ValidationResult;
import slf.xbb.validator.ValidatorImpl;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：xbb
 * @date ：Created in 2020/4/3 7:24 下午
 * @description：ItemService接口实现
 * @modifiedBy：
 * @version:
 */
@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private ItemDoMapper itemDoMapper;

    @Autowired
    private ItemStockDoMapper itemStockDoMapper;

    /**
     * 创建商品
     *
     * @param itemModel
     */
    @Override
    @Transactional(rollbackFor = BussinessException.class)
    public ItemModel createItem(ItemModel itemModel) throws BussinessException {
        // 校验参数
        ValidationResult result = validator.validationResult(itemModel);
        if (result.isHasErrors()) {
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, result.getErrMsg());
        }
        // convert itemModel to dataObject
        ItemDo itemDo = convertFromItemModelToItemDo(itemModel);
        // 写入数据库
        itemDoMapper.insertSelective(itemDo);
        // 返回创建完成的对象
        itemModel.setId(itemDo.getId());

        // 库存Do做同样的操作
        try {
            ItemStockDo itemStockDo = convertFromItemModelToItemStockDo(itemModel);
            itemStockDoMapper.insertSelective(itemStockDo);
        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }

        return getItemById(itemModel.getId());
    }

    /**
     * 商品列表浏览
     */
    @Override
    public List<ItemModel> listItem() {
        List<ItemDo> itemDoList = itemDoMapper.listItem();
        List<ItemModel> itemModelList = itemDoList.stream().map(itemDo -> {
            ItemStockDo itemStockDo = itemStockDoMapper.selectByItemId(itemDo.getId());
            ItemModel itemModel = convertToItemModel(itemDo, itemStockDo);
            return itemModel;
        }).collect(Collectors.toList());
        return itemModelList;
    }

    /**
     * 商品详情浏览
     *
     * @param id
     */
    @Override
    public ItemModel getItemById(Integer id) {
        ItemDo itemDo = itemDoMapper.selectByPrimaryKey(id);
        if (itemDo == null) {
            return null;
        }
        ItemStockDo itemStockDo = itemStockDoMapper.selectByItemId(itemDo.getId());
        return convertToItemModel(itemDo, itemStockDo);
    }

    private ItemDo convertFromItemModelToItemDo(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemDo itemDo = new ItemDo();
        BeanUtils.copyProperties(itemModel, itemDo);
        return itemDo;
    }

    private ItemStockDo convertFromItemModelToItemStockDo(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemStockDo itemStockDo = new ItemStockDo();
        itemStockDo.setStock(itemModel.getStock());
        itemStockDo.setItemId(itemModel.getId());
        return itemStockDo;
    }

    private ItemModel convertToItemModel(ItemDo itemDo, ItemStockDo itemStockDo) {
        if (itemDo == null) {
            return null;
        }
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDo, itemModel);
        itemModel.setStock(itemStockDo.getStock());
        return itemModel;
    }
}
