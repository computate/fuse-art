package com.heytate.frFR.cardiaque.requete;  

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrDocument;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessToken.Access;

import com.heytate.frFR.cardiaque.config.ConfigSite;
import com.heytate.frFR.cardiaque.contexte.EcouteurContexte;
import com.heytate.frFR.cardiaque.couverture.Couverture;
import com.heytate.frFR.cardiaque.utilisateur.UtilisateurSite;

public class RequeteSite extends RequeteSiteGen<Object> { 

	public RequeteSite() {
	}

	protected void _requeteSite(Couverture<RequeteSite> c) throws Exception { 
		c.o(this);
	}
	@Override protected void requeteSiteInit() throws Exception {
		requeteSite(this);
	}

	/**	L'objet de configuration du site. **/
	protected void _configSite(Couverture<ConfigSite> c) throws Exception {
	}
	@Override protected void configSiteInit() throws Exception {
	}

	/**	L'ecouteur de contexte du site pour obtenir des objets globals du contexte. **/
	protected void _ecouteurContexte(Couverture<EcouteurContexte> c) throws Exception {
		if(configSite != null)
			c.o(configSite.ecouteurContexte);
	}
	@Override protected void ecouteurContexteInit() throws Exception {
	}

	/**	Le jeton d'acces Keycloak pour l'utilisateur. **/
	protected void _jetonAcces(Couverture<AccessToken> c) throws Exception {
	}
	
	protected void _utilisateurNomDomaine(Couverture<String> c) {
		String o = jetonAcces == null ? "example.com" : null;
		c.o(o);
	}
	
	protected void _utilisateurNomEnsemble(Couverture<String> c) {
		String[] partis = StringUtils.split(utilisateurNomDomaine, ".");
		ArrayUtils.reverse(partis);
		String o = StringUtils.join(partis, ".");
		c.o(o);
	}

	/**	L'utilisateur de la requête. **/
	protected void _utilisateurSite(Couverture<UtilisateurSite> c) throws Exception { 
	}

	/**	Le sujet d'acces Keycloak pour l'utilisateur. 
	 \*	Aussi l'ID d'utilisateur cle primaire dans la base de donnees Keycloak. **/
	protected void _utilisateurId(Couverture<String> c) throws Exception {
		if(jetonAcces != null) {
			String o = jetonAcces.getSubject();
			c.o(o);
		}
	}

	/**	Le nom d'utilisateur prefere de l'utilisateur. **/
	protected void _utilisateurNom(Couverture<String> c) throws Exception {
		if(jetonAcces != null) {
			String o = jetonAcces.getPreferredUsername();
			c.o(o);
		}
	}

	/**	Le nom de famille de l'utilisateur. **/
	protected void _utilisateurNomFamille(Couverture<String> c) throws Exception {
		if(jetonAcces != null) {
			String o = jetonAcces.getFamilyName();
			c.o(o);
		}
	}

	/**	Le prenom de l'utilisateur. **/
	protected void _utilisateurPrenom(Couverture<String> c) throws Exception {
		if(jetonAcces != null) {
			String o = jetonAcces.getGivenName();
			c.o(o);
		}
	}

	/**	Le nom complet de l'utilisateur. **/
	protected void _utilisateurNomComplet(Couverture<String> c) throws Exception {
		if(jetonAcces != null) {
			String o = utilisateurPrenom + " " + utilisateurNomFamille;
			c.o(o);
		}
	}

	/**	Les rôles du royaume de l'utilisateur. **/
	protected void _utilisateurRolesRoyaume(HashSet<String> o) throws Exception {  
		if(jetonAcces != null) {
			Set<String> s = jetonAcces.getRealmAccess().getRoles();
			o.addAll(s);
		}
	}
	public boolean utilisateurRolesRoyaumeContient(String role) {
		boolean o = false;
		if(utilisateurRolesRoyaume != null) { 
			o = utilisateurRolesRoyaume.contains(role);
		}
		return o;
	}

	/**	Les rôles de la ressource de l'utilisateur. **/
	protected void _utilisateurRolesRessource(HashSet<String> o) throws Exception {
		if(configSite != null) {
			String siteIdKeycloak = configSite.siteIdKeycloak;
			if(siteIdKeycloak != null && jetonAcces != null) {
				Access utilisateurAccesRessource = jetonAcces.getResourceAccess(siteIdKeycloak);
				if(utilisateurAccesRessource != null && jetonAcces != null) {
					Set<String> s = utilisateurAccesRessource.getRoles();
					o.addAll(s);
				}
			}
		}
	}
	public boolean utilisateurRolesRessourceContient(String role) {
		boolean o = false;
		if(utilisateurRolesRessource != null) {
			o = utilisateurRolesRessource.contains(role);
		}
		return o;
	}

	public boolean utilisateurRolesContient(String role) {
		boolean o = 
			utilisateurRolesRoyaume != null && utilisateurRolesRoyaume.contains(role)
			|| utilisateurRolesRessource != null && utilisateurRolesRessource.contains(role)
		;
		return o;
	}

	protected void _xmlPile(Stack<String> o) {}
//
//	protected void _xmlElementParent(Couverture<String> c) {}
//
//	protected void _xmlElement(Couverture<String> c) {}

	/**	Le HttpServletRequest du requête. **/
	protected void _requete(Couverture<HttpServletRequest> c) throws Exception {
	}

	/**	Le HttpServletResponse du requête. **/
	protected void _reponse(Couverture<HttpServletResponse> c) throws Exception {
	}

	protected void _solrDocument(Couverture<SolrDocument> c) throws Exception {  
	}

	/**	L'ecrivain pour ecrirer le resultat du reponse. **/
	protected void _ecrivain(Couverture<PrintWriter> c) throws Exception {
		if(reponse != null) {
			reponse.setCharacterEncoding("UTF-8");
			PrintWriter o = reponse.getWriter();
			c.o(o);
		}
	}

	/**	Si la page vu etait achete par l'utilisateur. **/
	protected void _pageAchete(Couverture<Boolean> c) throws Exception { 
		c.o(false);
	}

	/**	Si la page vu etait achete par l'utilisateur. **/
	protected void _pageAdmin(Couverture<Boolean> c) throws Exception { 
		c.o(false);
	}
	
	protected void _h(Couverture<String> c) throws Exception {}
	
	protected void _chiffrementCrypter(Couverture<Cipher> c) throws Exception {
		if(!StringUtils.isEmpty(h)) {
			c.o(Cipher.getInstance("AES"));
		}
	}
	
	protected void _chiffrementDecrypter(Couverture<Cipher> c) throws Exception {
		if(!StringUtils.isEmpty(h)) {
			c.o(Cipher.getInstance("AES"));
		}
	}
	
	protected void _digestMessage(Couverture<MessageDigest> c) throws Exception {    
		if(!StringUtils.isEmpty(h)) {
			c.o(MessageDigest.getInstance("SHA-1"));
		}
	}
	
	protected void _sel(Couverture<String> c) throws Exception {
	}
	
	protected void _cle(Couverture<byte[]> c) throws Exception {
		if(!StringUtils.isEmpty(h)) {
			c.o(Arrays.copyOf(digestMessage.digest((sel + h).getBytes("UTF-8")), 16));
		}
	}
	
	protected void _aleatoireSecurise(SecureRandom o) throws Exception {}  
	
	protected void _specCleSecrete(Couverture<SecretKeySpec> c) throws Exception {
		if(!StringUtils.isEmpty(h)) {
			SecretKeySpec specCleSecrete = new SecretKeySpec(cle, "AES");
			chiffrementCrypter.init(Cipher.ENCRYPT_MODE, specCleSecrete);
			chiffrementDecrypter.init(Cipher.DECRYPT_MODE, specCleSecrete);
			c.o(specCleSecrete);
		}
	}
	
	public byte[] crypterOctets(String o) throws Exception {
		byte[] octetsNonCrypte = o.getBytes();
		byte[] encryptedByte = null;
		encryptedByte = chiffrementCrypter.doFinal(octetsNonCrypte);
		return encryptedByte;
	}
	
	public String decrypterOctets(byte[] octetsCrypte) throws Exception {
		String texteNonCrypte = null;
		byte[] decryptedByte = chiffrementDecrypter.doFinal(octetsCrypte);
		texteNonCrypte = new String(decryptedByte);
		return texteNonCrypte;
	}
	
	public String crypterStr(String o) throws Exception {
		String texteCrypte = null;     
		if(chiffrementCrypter != null) {
			byte[] octetsNonCrypte = o.getBytes();
			byte[] encryptedByte = chiffrementCrypter.doFinal(octetsNonCrypte);
			Base64.Encoder codeur = Base64.getEncoder();
			texteCrypte = codeur.encodeToString(encryptedByte);
		}
		return texteCrypte;
	}
	
	public String decrypterStr(String o) throws Exception {
		String texteNonCrypte = null;
		if(o != null && chiffrementDecrypter != null) {
			Base64.Decoder decodeur = Base64.getDecoder();
			byte[] octetsCrypte = decodeur.decode(o);
			byte[] decryptedByte = chiffrementDecrypter.doFinal(octetsCrypte);
			texteNonCrypte = new String(decryptedByte);
		}
		return texteNonCrypte;
	}
//
//	@org.junit.Test public void genererClasseChaine() throws Exception {
//		EcouteurContexte ecouteurContexte = new EcouteurContexte();
//		ecouteurContexte.initialiserLoinEcouteurContexte();
//
//		LangueSite langue = new LangueSite();
//		langue.identifiant("frFR");
//		langue.initialiserLoinLangueSite();
//
//		RequeteSite requeteSite = new RequeteSite();
//		requeteSite.langue(langue);
//		requeteSite.initialiserLoinRequeteSite();
//
//		GenerateurClasse generateurClasse = new GenerateurClasse();
//		generateurClasse.cheminConfiguration(ecouteurContexte.cheminConfig);
//		String cheminRessource = ecouteurContexte.configSite.cheminProjet + "/src/main/java/" + getClass().getCanonicalName().replace(".", "/") + ".java";
//		generateurClasse.requeteSite(requeteSite);
//		generateurClasse.cheminRessource(cheminRessource);
//		generateurClasse.initialiserLoinGenerateurClasse(requeteSite);
//		System.out.println(generateurClasse.texteClasse.toString());
//	}
}
