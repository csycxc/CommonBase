package com.banry.pscm.commonbase.service.modelPOJO;

import java.util.List;

/**
 * @author chenshiyu
 *
 */
public class TreeNode {

	private String id;
	private String name;
	private String skill;
	private String divlevel;
	private String pId;

	private boolean open;
	private boolean draggable;
	private String url;
	private String target;
	private String dataUrl;
	private String isLeaf;
	private List<TreeNode> children;
	

	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public boolean isDraggable() {
		return draggable;
	}
	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
	}
	public String getDataUrl() {
		return dataUrl;
	}
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}
	public String getDivlevel() {
		return divlevel;
	}
	public void setDivlevel(String divlevel) {
		this.divlevel = divlevel;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", name=" + name + ", skill=" + skill + ", divlevel=" + divlevel + ", pId=" + pId
				+ ", open=" + open + ", draggable=" + draggable + ", url=" + url + ", target=" + target + ", dataUrl="
				+ dataUrl + ", isLeaf=" + isLeaf + ", children=" + children + "]";
	}
	
}
