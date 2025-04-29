package ksmybatis.admin.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmybatis.admin.product.domain.Product;

@Mapper
public interface ProductMapper {
	
	
	
	// 선택한 상품 삭제
	int removeProductsByProdCode(String productCode);
	
	
	// 판매자가 등록한 상품 삭제
	int removeProductsBySellerId(String sellerId);
	
	//상품 수정
	int modifyProduct(Product product);
	
	// 상품 정보 조회
	Product getProductInfoByCode(String productCode);

	//상품 등록
	int addProduct(Product product);
	
	//상품 목록 조회
	List<Product> getProductList();
}
