package fr.afcepf.atod.wine.data.product.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.afcepf.atod.wine.data.impl.DaoGeneric;
import fr.afcepf.atod.wine.data.product.api.IDaoProductFeature;
import fr.afcepf.atod.wine.entity.ProductFeature;


@Service
@Transactional
public class DaoProductFeature extends DaoGeneric<ProductFeature, Integer> implements IDaoProductFeature {

}
