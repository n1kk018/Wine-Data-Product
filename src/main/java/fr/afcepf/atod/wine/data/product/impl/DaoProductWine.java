package fr.afcepf.atod.wine.data.product.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.afcepf.atod.wine.data.impl.DaoGeneric;
import fr.afcepf.atod.wine.data.product.api.IDaoProductWine;
import fr.afcepf.atod.wine.entity.ProductWine;


@Service
@Transactional
public class DaoProductWine extends DaoGeneric<ProductWine, Integer> implements IDaoProductWine {

}

