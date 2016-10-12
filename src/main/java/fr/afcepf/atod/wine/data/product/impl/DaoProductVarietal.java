package fr.afcepf.atod.wine.data.product.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.afcepf.atod.wine.data.impl.DaoGeneric;
import fr.afcepf.atod.wine.data.product.api.IDaoProductVarietal;
import fr.afcepf.atod.wine.entity.ProductVarietal;


@Service
@Transactional
public class DaoProductVarietal extends DaoGeneric<ProductVarietal, Integer> implements IDaoProductVarietal {

}

