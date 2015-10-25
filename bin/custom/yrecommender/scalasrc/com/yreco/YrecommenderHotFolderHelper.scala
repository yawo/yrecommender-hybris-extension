package com.yreco
import java.util
import java.util.Date

import de.hybris.platform.catalog.jalo.ProductReference
import de.hybris.platform.core.model.product.ProductModel
import de.hybris.platform.jalo.JaloSession
import de.hybris.platform.jalo.product.Product
import de.hybris.platform.jalo.user.UserManager
import org.apache.log4j.Logger

import scala.collection.JavaConversions._

object YrecommenderHotFolderHelper{
  val LOG   = Logger.getLogger("com.yreco.YrecommenderHotFolderHelper")
  val admin = UserManager.getInstance().getAdminEmployee
  def cleanOldReferences(referenceTypeCode:String, product:Product,  fromDate:Date) ={
    JaloSession.getCurrentSession.setUser(admin)
    require(product!=null && referenceTypeCode!=null && fromDate!=null,"[product,referenceTypeCode and fromDate] must each be not null")
    LOG.info(s"Cleaning for ${referenceTypeCode}, ${product.getCode} at ${fromDate}")
    product.getAttribute(ProductModel.PRODUCTREFERENCES)
      .asInstanceOf[util.Collection[ProductReference]]
      .filter((ref) => referenceTypeCode.equalsIgnoreCase(ref.getReferenceType().getCode) && fromDate.after(ref.getModificationTime))
      .foreach(_.remove())
    //refs.foreach((ref) => LOG.info(s"${ref.getCreationTime()} .before (${fromDate})"))
  }
}