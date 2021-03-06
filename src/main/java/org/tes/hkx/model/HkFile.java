package org.tes.hkx.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.tes.hkx.lib.HkobjectType;
import org.tes.hkx.lib.HkpackfileType;
import org.tes.hkx.lib.HksectionType;
import org.tes.hkx.lib.ext.hkRootLevelContainer;
import org.tes.hkx.model.visitors.HkTreeReferencedList;
import org.tes.hkx.model.visitors.RenumberingVisitor;

public class HkFile {

	protected JAXBElement<HkpackfileType> wrapper = null;
	protected HkpackfileType hkx = null;
	
	hkRootLevelContainer root;
	ArrayList<HkobjectType> variants;
	
	protected int startingKey = 1;

	protected String getFirstKey() {
		return "#"+String.format("%04d", startingKey);
		
	}
	
	public HkFile() {
		hkx = new HkpackfileType();
		wrapper = new JAXBElement<HkpackfileType>(new QName("hkpackfile"), HkpackfileType.class, hkx);
		hkx.setClassversion((byte) 8);
		hkx.setContentsversion("hk_2010.2.0-r1");
		HksectionType section = new HksectionType();
		section.setName("__data__");
		root = new hkRootLevelContainer();
		hkx.setHksection(section);
		section.getHkobject().add(root);
		root.setKey(getFirstKey());
		hkx.setToplevelobject(root.getKey());
	}
	
	public HkFile(JAXBElement<HkpackfileType> hkx) {
		this.wrapper = hkx;
		this.hkx = hkx.getValue();
		root = getTypedObject(this.hkx.getToplevelobject());
	}

	public void setHkClassVersion(Byte version) {
		hkx.setClassversion(version);
	}

	public void setHkContentsVersion(String version) {
		hkx.setContentsversion(version);
	}

	public List<HkobjectType> getObjects() {
		return hkx.getHksection().getHkobject();
	}

	public HkobjectType getObject(String key) {
		for (HkobjectType o : hkx.getHksection().getHkobject())
			if (o.getKey().equals(key))
				return o;
		return null;
	}

	public <T extends HkobjectType> T getTypedObject(String key) {
		for (HkobjectType o : hkx.getHksection().getHkobject())
			if (o.getKey().equals(key))
				return (T) o;
		return null;
	}

	public hkRootLevelContainer getRoot() {
		return root;
	}

	public void resetKeys() throws JAXBException {
		removeUnreferenced();
		root.accept(new RenumberingVisitor(startingKey));
		hkx.setToplevelobject(root.getKey());
	}

	private void removeUnreferenced() {
		List<HkobjectType> referenced = root.accept(new HkTreeReferencedList());
		List<HkobjectType> unreferenced = new ArrayList<>();
		for (HkobjectType o : hkx.getHksection().getHkobject()) {
			if (!referenced.contains(o)) {
				unreferenced.add(o);
			}
		}
		for (HkobjectType o : unreferenced) {
			hkx.getHksection().getHkobject().remove(o);
		}
	}
	
	public <T extends HkobjectType> T createObject(Class<T> objectClass) throws Exception {
		T toAdd = objectClass.newInstance();
		hkx.getHksection().getHkobject().add(toAdd);
		return toAdd;
	}
	
	public boolean deleteObject(HkobjectType object) {
		return hkx.getHksection().getHkobject().remove(object);
	}
	
	public JAXBElement<HkpackfileType> getWrapper() {
		return wrapper;
	}
}
