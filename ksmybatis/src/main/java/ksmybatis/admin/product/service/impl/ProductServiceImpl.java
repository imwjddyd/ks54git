package ksmybatis.admin.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmybatis.admin.orders.mapper.OrdersMapper;
import ksmybatis.admin.product.domain.Product;
import ksmybatis.admin.product.mapper.ProductMapper;
import ksmybatis.admin.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService{

	private final ProductMapper productMapper;
	private final OrdersMapper ordersMapper;
	
	
	
	
	
	
	// 상품삭제
	@Override
	public boolean removeProductByCode(String productCode) {
		
		int delCount = 0;
		// 주문 상세
		delCount += ordersMapper.removeOrderItemsByProductCode(productCode);
		
		// 상품
		delCount += productMapper.removeProductsByProdCode(productCode);
		
		boolean isDel = delCount > 0 ? true : false;
		
		return isDel;
	}
	
	
	
	
	
	
	
	// 상품수정
	@Override
	public void modifyProduct(Product product) {

		productMapper.modifyProduct(product);
	}
	
	
	@Override
	public Product getProductInfoByCode(String prodCode) {
		
		return productMapper.getProductInfoByCode(prodCode);
	}
	
	
	// 상품 등록
	@Override
	public void addProduct(Product product) {
		log.info("상품등록 전 : {}", product);
		
		productMapper.addProduct(product);		
		
		log.info("상품등록 후 : {}", product);
	}
	
	// 상품 목록 조회
	@Override
	public List<Product> getProductList() {
		List<Product> productList = productMapper.getProductList();
		return productList;
	}
}
