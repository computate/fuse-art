package com.heytate.frFR.cardiaque.page.parti;  

import com.heytate.frFR.cardiac.chaine.Chaine;
import com.heytate.frFR.cardiac.page.parti.IconeGen;
import com.heytate.frFR.cardiaque.couverture.Couverture;
import com.heytate.frFR.cardiaque.page.MiseEnPage;

public class Icone extends IconeGen<PageParti> { 

	public Icone() {
	}

	protected void _type(Chaine o) throws Exception {
	}
	protected void _nom(Chaine o) throws Exception {
	}

	protected void _page_(Couverture<MiseEnPage> c) throws Exception {}

	public void htmlAvant() {
		page_.e("svg").a("class", "fa-icon w3-padding-4 w3-margin-right-4 ").f();
			page_.e("use").a("xlink:href", "/sprites/", type, ".svg#", nom).f().g("use");
	}
	public void htmlMilieu() {
	}
	public void htmlApres() {
		page_.g("svg");
	}
	public void html() {
		htmlAvant();
		htmlMilieu();
		htmlApres();
	}

		@Override public Chaine partiH3() {
			return null;
		}
		@Override public Chaine partiH3Court() {
			return null;
		}

		@Override public Chaine partiH4() {
			return null;
		}
		@Override public Chaine partiH4Court() {
			return null;
		}

		@Override
		public void htmlCourt() {
			// TODO Auto-generated method stub
			
		}

	//////////
	// code //
	//////////

	public void codeAvant() { 
	}
	public void codeMilieu() {
	}
	public void codeApres() {
	}
	public void code() {
		codeAvant();
		codeMilieu();
		codeApres();
	}
}
