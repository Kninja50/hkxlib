//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.26 at 09:23:25 AM CEST 
//

package org.tes.hkx.lib;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorNode;
import org.tes.hkx.model.IHkParentVisitor;
import org.tes.hkx.model.IHkParented;
import org.tes.hkx.model.IHkVisitable;
import org.tes.hkx.model.IHkVisitor;

/**
 * <p>
 * Java class for hkobjectType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="hkobjectType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hkparam" type="{}hkparamType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="class" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="signature" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hkobjectType", propOrder = { "hkparam" })
@XmlDiscriminatorNode("@signature")
public class HkobjectType implements IHkVisitable, IHkParented {

	protected List<HkparamType> hkparam;
	@XmlID
	@XmlAttribute(name = "name")
	protected String key;
	@XmlAttribute(name = "class")
	protected String clazz;
	@XmlAttribute(name = "signature")
	protected String signature;
	
	@XmlTransient
	Set<IHkVisitable> parents = new HashSet<>();
	
	@Override
	public Set<IHkVisitable> getParents() {
		return parents;
	}
	
	@Override
	public void addParent(IHkVisitable parent) {
		parents.add(parent);
	}
	
	@Override
	public boolean removeParent(IHkVisitable parent) {
		return parents.remove(parent);
	}
	

	/**
	 * Gets the value of the hkparam property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the hkparam property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getHkparam().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link HkparamType }
	 * 
	 * 
	 */
	public List<HkparamType> getHkparam() {
		if (hkparam == null) {
			hkparam = new ArrayList<HkparamType>();
		}
		return this.hkparam;
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setKey(String value) {
		this.key = value;
	}

	/**
	 * Gets the value of the clazz property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getClazz() {
		return clazz;
	}

	/**
	 * Sets the value of the clazz property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setClazz(String value) {
		this.clazz = value;
	}

	/**
	 * Gets the value of the signature property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * Sets the value of the signature property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSignature(String value) {
		this.signature = value;
	}

	@Override
	public <T> T accept(IHkVisitor<T> visitor) {
		visitor.visit(this);
		return visitor.getResults();
	}
	
	@Override
	public <T> T accept(IHkParentVisitor<T> visitor, IHkVisitable parent) {
		visitor.visit(this,parent);
		return visitor.getResults();
	}
	
	@Override
	public String toString() {
		return this.getClazz()+" "+this.getKey();
	}

	public HkobjectType() {};
	
	public HkobjectType(HkobjectType toClone, boolean deepCopy) {
		if (toClone == null)
			return;
		setClazz(toClone.getClazz());
		setKey(toClone.getKey());
		setSignature(toClone.getSignature());
	}
	
	@Override
	@XmlTransient
	public IHkParented findRoot(IHkParented child) {
		IHkParented p = null;
		if (getParents().isEmpty())
			return child;
		for (IHkVisitable parent : child.getParents()) {
			if (parent instanceof IHkParented) {
				IHkParented pn = findRoot((IHkParented) parent);
				if (pn != null) {
					p = pn;
				}
			}
		}
		return p;
	}
	
	@XmlTransient
	private IHkParented findParentWithClass(IHkParented child, Class<?> c) {
		IHkParented p = null;
		if (getParents().isEmpty() && !child.getClass().equals(c))
			return null;
		if (child.getClass().equals(c))
			return child; 
		for (IHkVisitable parent : child.getParents()) {
			if (parent instanceof IHkParented) {
				IHkParented pn = findParentWithClass((IHkParented) parent,c);
				if (pn != null) {
					p = pn;
				}
			}
		}
		return p;
	}

	@Override
	@XmlTransient
	public <T> T findParentWithClass(Class<T> c) {
		return (T) findParentWithClass(this, c);
	}
}
