package com.bruno.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the hours database table.
 * 
 */
@Entity
@Table(name="hours")
@NamedQuery(name="Hour.findAll", query="SELECT h FROM Hour h")
public class Hour implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="close_fri")
	private Timestamp closeFri;

	@Column(name="close_mon")
	private Timestamp closeMon;

	@Column(name="close_sat")
	private Timestamp closeSat;

	@Column(name="close_sun")
	private Timestamp closeSun;

	@Column(name="close_th")
	private Timestamp closeTh;

	@Column(name="close_tu")
	private Timestamp closeTu;

	@Column(name="close_wed")
	private Timestamp closeWed;

	@Column(name="open_fri")
	private Timestamp openFri;

	@Column(name="open_mon")
	private Timestamp openMon;

	@Column(name="open_sat")
	private Timestamp openSat;

	@Column(name="open_sun")
	private Timestamp openSun;

	@Column(name="open_th")
	private Timestamp openTh;

	@Column(name="open_tu")
	private Timestamp openTu;

	@Column(name="open_wed")
	private Timestamp openWed;

	//bi-directional many-to-one association to Venue
	@ManyToOne
	private Venue venue;

	public Hour() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCloseFri() {
		return this.closeFri;
	}

	public void setCloseFri(Timestamp closeFri) {
		this.closeFri = closeFri;
	}

	public Timestamp getCloseMon() {
		return this.closeMon;
	}

	public void setCloseMon(Timestamp closeMon) {
		this.closeMon = closeMon;
	}

	public Timestamp getCloseSat() {
		return this.closeSat;
	}

	public void setCloseSat(Timestamp closeSat) {
		this.closeSat = closeSat;
	}

	public Timestamp getCloseSun() {
		return this.closeSun;
	}

	public void setCloseSun(Timestamp closeSun) {
		this.closeSun = closeSun;
	}

	public Timestamp getCloseTh() {
		return this.closeTh;
	}

	public void setCloseTh(Timestamp closeTh) {
		this.closeTh = closeTh;
	}

	public Timestamp getCloseTu() {
		return this.closeTu;
	}

	public void setCloseTu(Timestamp closeTu) {
		this.closeTu = closeTu;
	}

	public Timestamp getCloseWed() {
		return this.closeWed;
	}

	public void setCloseWed(Timestamp closeWed) {
		this.closeWed = closeWed;
	}

	public Timestamp getOpenFri() {
		return this.openFri;
	}

	public void setOpenFri(Timestamp openFri) {
		this.openFri = openFri;
	}

	public Timestamp getOpenMon() {
		return this.openMon;
	}

	public void setOpenMon(Timestamp openMon) {
		this.openMon = openMon;
	}

	public Timestamp getOpenSat() {
		return this.openSat;
	}

	public void setOpenSat(Timestamp openSat) {
		this.openSat = openSat;
	}

	public Timestamp getOpenSun() {
		return this.openSun;
	}

	public void setOpenSun(Timestamp openSun) {
		this.openSun = openSun;
	}

	public Timestamp getOpenTh() {
		return this.openTh;
	}

	public void setOpenTh(Timestamp openTh) {
		this.openTh = openTh;
	}

	public Timestamp getOpenTu() {
		return this.openTu;
	}

	public void setOpenTu(Timestamp openTu) {
		this.openTu = openTu;
	}

	public Timestamp getOpenWed() {
		return this.openWed;
	}

	public void setOpenWed(Timestamp openWed) {
		this.openWed = openWed;
	}

	public Venue getVenue() {
		return this.venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

}