package freshfridge.freshfridgebackend.service;

import freshfridge.freshfridgebackend.entity.ProductInKoelkast;

import java.util.List;

public interface ProductInKoelkastService {

    ProductInKoelkast addProductInKoelkast(ProductInKoelkast pik,
                                           Integer productId,
                                           Integer koelkastId,
                                           Integer scannerId);

    ProductInKoelkast getProductInKoelkast(Integer pikId);

    List<ProductInKoelkast> getProductenInKoelkast(Integer koelkastId);

    List<ProductInKoelkast> getProductenVanGebruiker(Integer gebruikernr);

    void deleteProductInKoelkast(Integer pikId);
}
