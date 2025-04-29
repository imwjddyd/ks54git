package ksmybatis.admin.product.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ksmybatis.admin.member.domain.Member;
import ksmybatis.admin.product.domain.Product;
import ksmybatis.admin.product.service.ProductService;
import ksmybatis.admin.vendor.domain.Vendor;
import ksmybatis.admin.vendor.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

	private final ProductService productService;
	private final VendorService vendorService;
	
	
	
	@PostMapping("/removeProduct")
	@ResponseBody
	public boolean removeProduct(@RequestParam(name="productCode") String productCode) {
		log.info("삭제할 상품코드: {}", productCode);

		boolean isDel = productService.removeProductByCode(productCode);
		

		
		return isDel;
	}
	
	
	
	
	
	
	
	
	
	@PostMapping("/modifyProduct")
	public String modifyProduct(Product product, RedirectAttributes reAttr) {
		
		productService.modifyProduct(product);
		
		reAttr.addAttribute("prodCode", product.getProdCode());
		
		
		return "redirect:/admin/product/modifyProduct";
	}
	
	
	@GetMapping("/modifyProduct")
	public String modifyProduct(String prodCode, Model model) {
		
		Product productInfo = productService.getProductInfoByCode(prodCode);
		List<Vendor> vendorList = vendorService.getVendorList();
		
		model.addAttribute("title", "상품 수정");
		model.addAttribute("productInfo", productInfo);
		model.addAttribute("vendorList", vendorList);
		
		
		return "admin/product/modifyProductView";
	}
	
	
	@PostMapping("/addProduct")
	public String addProduct(Product product) {
		
		productService.addProduct(product);
		
		return "redirect:/admin/product/productList";
		
	}
	
	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		
		List<Vendor> vendorList = vendorService.getVendorList();
		
		model.addAttribute("title", "상품 등록");
		model.addAttribute("vendorList", vendorList);
		
		
		return "admin/product/addProductView";
	}
	
	
	@GetMapping("/productList")
	public String getProductList(Model model) {
		
		List<Product> productList = productService.getProductList();
		
		model.addAttribute("title", "상품 목록");
		model.addAttribute("productList", productList);
		
		return "admin/product/productListView";
	}
}
