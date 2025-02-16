package com.csh.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.estore.Product;
import com.csh.entity.estore.ProductCategory;
import com.csh.service.ProductCategoryService;

@Controller("productCategoryController")
@RequestMapping("console/productCategory")
public class ProductCategoryController extends BaseController{

    @Resource(name="productCategoryServiceImpl")
    private ProductCategoryService productCategoryService;
    
    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        model.addAttribute("productCategoryTree", productCategoryService.findTree());
        return "/productCategory/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ProductCategory productCategory, Long parentId, Long[] brandIds, RedirectAttributes redirectAttributes) {
        productCategory.setParent(productCategoryService.find(parentId));
        if (!isValid(productCategory)) {
            return ERROR_VIEW;
        }
        productCategory.setTreePath(null);
        productCategory.setGrade(null);
        productCategory.setChildren(null);
        productCategory.setProducts(null);
        productCategory.setParameterGroups(null);
        productCategory.setAttributes(null);
        productCategoryService.save(productCategory);
        return "redirect:list.jhtml";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model) {
        ProductCategory productCategory = productCategoryService.find(id);
        model.addAttribute("productCategoryTree", productCategoryService.findTree());
        model.addAttribute("productCategory", productCategory);
        model.addAttribute("children", productCategoryService.findChildren(productCategory));
        return "/productCategory/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ProductCategory productCategory, Long parentId, Long[] brandIds, RedirectAttributes redirectAttributes) {
        productCategory.setParent(productCategoryService.find(parentId));
        if (!isValid(productCategory)) {
            return ERROR_VIEW;
        }
        if (productCategory.getParent() != null) {
            ProductCategory parent = productCategory.getParent();
            if (parent.equals(productCategory)) {
                return ERROR_VIEW;
            }
            List<ProductCategory> children = productCategoryService.findChildren(parent);
            if (children != null && children.contains(parent)) {
                return ERROR_VIEW;
            }
        }
        productCategoryService.update(productCategory, "treePath", "grade", "children", "products", "parameterGroups", "attributes", "promotions");
        return "redirect:list.jhtml";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.addAttribute("productCategoryTree", productCategoryService.findTree());
        return "/productCategory/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long id) {
        ProductCategory productCategory = productCategoryService.find(id);
        if (productCategory == null) {
            return ERROR_MESSAGE;
        }
        Set<ProductCategory> children = productCategory.getChildren();
        if (children != null && !children.isEmpty()) {
            return Message.error("csh.productCategory.deleteExistChildrenNotAllowed");
        }
        Set<Product> products = productCategory.getProducts();
        if (products != null && !products.isEmpty()) {
            return Message.error("csh.productCategory.deleteExistProductNotAllowed");
        }
        productCategoryService.delete(id);
        return SUCCESS_MESSAGE;
    }
}
