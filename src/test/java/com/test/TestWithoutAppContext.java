package com.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.castor.core.util.Base64Decoder;
import org.exolab.castor.xml.Marshaller;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.assessment.data.TestCase;
import com.assessment.data.TestCases;
import com.assessment.data.User;
import com.assessment.eclipseche.config.response.WorkspaceResponse;
import com.assessment.eclipseche.services.EclipseCheService;
import com.assessment.services.JobDescriptionParser;
import com.assessment.services.impl.JobDescriptionParserImpl;
import com.assessment.web.dto.CustomArgs;
import com.assessment.web.dto.JobDescriptionParseResponseDto;
import com.assessment.web.dto.SamlResponseMiniDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestWithoutAppContext {
	
	String url = "https://ide.yaksha.online/assessment/getAssessmentURLForLMSLearner?testName=${TEST_NAME}&companyId=IH&learnerEmail=testuser1@iihtinternal.com&learnerfirstName=user&learnerLastName=eee";
	
	@Test
	public void testIntDiv(){
		
		int n1 = 45;
		int n2 = 100;
		Float num = n1 * 1.0f / n2;
		System.out.println(Math.round(num));
	}
	
	
	public String extractImageUrl(String text){
		 final String s = text;
	        final String regex = "(?<=<img src=\")[^\"]*";
	        final Pattern p = Pattern.compile(regex);
	        final Matcher m = p.matcher(s);
	        while (m.find()) {
	        	String str = m.group();
	            System.out.println("image available "+str);
	            return str;
	        }
	      return null;
	}
	
	@Test
	public void testExtrcatImageUrl(){
		String s = "wdwdewiiiiiiiiiiii<<img src=\"mysrc\"";
		System.out.println(extractImageUrl(s));
	}
	
	@Test
	public void testGetTestIds() throws Exception{
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<String> lines = FileUtils.readLines(new File("Test_Names.txt"), "UTF-8");
		List<String> op = new ArrayList<>();
		Map<String, String> map = new HashMap<String, String>();
		List<String> missing = new ArrayList<>();
		int count = 0;
		for(String test : lines){
			if(map.containsKey(test)){
				System.out.println(map.get(test));
				op.add(test+","+map.get(test));
			}
			else{
				String en = URLEncoder.encode(test);
				String u = url.replace("${TEST_NAME}", en);
				URL url2 = new URL(u);
				HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("securitytoken", "QWERTY");
				conn.setDoOutput(true);
				conn.setDoInput(true);
				String resp = IOUtils.toString(conn.getInputStream()); 
				String temp = resp.substring(resp.indexOf("&testId")+7, resp.length());
				System.out.println("test name is  "+test+" part is "+temp);
					if(temp.equals("G_TEST_NAME")){
						map.put(test, "NA");
						op.add(test+",NA");
					}
					else{
							if(temp.length() > 3000){
								map.put(test, "NA");
								op.add(test+",NA");
								missing.add(test);
								count++;
								System.out.println("missing count is "+count);
							}
							else{
								String temp2 = temp.substring(1, temp.indexOf("&"));
								map.put(test, temp2);
								op.add(test+","+temp2);
							}
						
						
					}
					conn.disconnect();
			}
			
		}
		FileUtils.writeLines(new File("testids_op.csv"), op);
		FileUtils.writeLines(new File("testids_missing.csv"), missing);
	   
	}
	
	@Test
	public void testCreateLdapUser() throws NamingException{
		  Hashtable<String, Object> env = new Hashtable<String, Object>(11);
	        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        env.put(Context.PROVIDER_URL, "ldap://ldap.yaksha.online:389");
	        env.put(Context.SECURITY_AUTHENTICATION, "simple");
	        env.put(Context.SECURITY_PRINCIPAL, "cn=iihtadmin,dc=yaksha,dc=online");
	        env.put(Context.SECURITY_CREDENTIALS, "rabbit@098");
	        InitialDirContext ctx = new InitialDirContext(env);
	        
	        BasicAttributes attrs = new BasicAttributes();
	        Attribute classes = new BasicAttribute("objectclass");
	        classes.add("personinfo");
	        attrs.put(classes);
	        attrs.put("cn", "testuserdev1");
	        attrs.put("sn", "sn1");
	        attrs.put("givenName", "testuserdev1");
	        attrs.put("mail", "jatinsut@yahoo.com");
	        attrs.put("dc", "yaksha");
	        attrs.put("dc", "online");
	        String entryDN = " uid=test1,ou=people,dc=yaksha,dc=online"; 
            //ctx.createSubcontext("ldap://ldap.yaksha.online:389/cn=iiht,dc=yaksha,dc=online",       attrs);
           ctx.createSubcontext(entryDN, attrs);
	        System.out.println("User has successfully been created");
	}
	
	@Test
	public void testCreateUser2Ldap(){
		Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://ldap.yaksha.online:389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=iihtadmin,dc=yaksha,dc=online");
        env.put(Context.SECURITY_CREDENTIALS, "rabbit@098");
       // TODO code application logic here  

                 // entry's DN 
  String entryDN = "uid=abc@def.com,ou=people,dc=yaksha,dc=online";  

   // entry's attributes  

   Attribute cn = new BasicAttribute("cn", "Abc Def");  
   Attribute sn = new BasicAttribute("sn", "Def");  
   Attribute mail = new BasicAttribute("mail", "abc@def.com");  
   Attribute phone = new BasicAttribute("telephoneNumber", "+1 222 3334444");   
       Attribute oc = new BasicAttribute("objectClass");  
       Attribute userPassword = new BasicAttribute("userpassword", "12345");
   oc.add("top");  
   oc.add("person");  
   oc.add("organizationalPerson");  
   oc.add("inetOrgPerson");  
   DirContext ctx = null;  

   try {  
       // get a handle to an Initial DirContext  
       ctx = new InitialDirContext(env);  

       // build the entry  
       BasicAttributes entry = new BasicAttributes();  
       entry.put(cn);  
       entry.put(sn);  
       entry.put(mail);  
       entry.put(phone);  

       entry.put(oc); 
       entry.put(userPassword); 

       // Add the entry  

       ctx.createSubcontext(entryDN, entry);  
//          System.out.println( "AddUser: added entry " + entryDN + ".");  

   } catch (NamingException e) {  
       System.err.println("AddUser: error adding entry." + e);  
   }  
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCreateHash(){
		String str = "joinfullName=Akash%20Singh&meetingId=M123&password=222xaU99dPTE9etdXDpnJttY2IxA5ki8gLJGNNVJjHTl4";
		System.out.println(DigestUtils.shaHex(str));
	}
	
	@Test
	public void testValidateSchemaWithTestCasesXml(){
		try {
            SchemaFactory factory = 
                    SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
           // factory.setProperty("xmlns:java", "http://java.sun.com");
           // factory.setProperty("xsi:type", "java:com.assessment.data.TestCase");
            Schema schema = factory.newSchema(new File("testcases.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File("testcases.xml")));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            e.printStackTrace();
        }
	}
	
	@Test
	public void testCreateRSAKeys() throws NoSuchAlgorithmException, Exception{
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);
		KeyPair kp = kpg.generateKeyPair();
		Key pub = kp.getPublic();
		Key pvt = kp.getPrivate();
		
		String outFile = "private";
		FileOutputStream out = new FileOutputStream(outFile + ".key");
		out.write(pvt.getEncoded());
		out.close();

		out = new FileOutputStream("public" + ".pub");
		out.write(pvt.getEncoded());
		out.close();
		
		System.err.println("Private key format: " + pvt.getFormat());
		// prints "Private key format: PKCS#8" on my machine

		System.err.println("Public key format: " + pub.getFormat());
		// prints "Public key format: X.509" on my machine
	}
	
	@Test
	public void testCreateAesKey() throws Exception{
		KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128); // The AES key size in number of bits
        SecretKey secKey = generator.generateKey();
        byte[] key = secKey.getEncoded();
        String secretkey = DatatypeConverter.printHexBinary(key);
        FileUtils.write(new File("secret.key"), secretkey);
	}
	
	@Test
	public void testEncryyptAndDecrypt() throws Exception{
		java.security.Security.addProvider(
		         new org.bouncycastle.jce.provider.BouncyCastleProvider()
		);
		byte[] pub = FileUtils.readFileToByteArray(new File("public.pub"));
		X509EncodedKeySpec ks = new X509EncodedKeySpec(pub);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PublicKey pubkey = kf.generatePublic(ks);
		String message = "jatin.sutarin@gmail.com";
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE	, pubkey);
		byte[] encryptedessage = cipher.doFinal(message.getBytes());
		
		byte[] priv = FileUtils.readFileToByteArray(new File("private.key"));
		PKCS8EncodedKeySpec ks1 = new PKCS8EncodedKeySpec(priv);
		KeyFactory kf1 = KeyFactory.getInstance("RSA");
		PrivateKey pvt = kf.generatePrivate(ks);
		Cipher cipher1 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher1.init(Cipher.DECRYPT_MODE, pvt);
        String decryptedMessage =  new String(cipher1.doFinal(priv));
        System.out.println("decry msg "+decryptedMessage);
	}
	
	@Test
	public void testMarshalXmlToJava() throws Exception{
		TestCases testCases = new TestCases();
		TestCase case1 = new TestCase("testFindNoOfOccurrences1", "na", 2, true, "Functional", "5");
		TestCase case2 = new TestCase("testFindNoOfOccurrences2", "na", 2, true, "Functional", "1");
		TestCase case3 = new TestCase("testFindNoOfOccurrences3", "na", 2, true, "Functional", "5");
		List<TestCase> cases = new ArrayList<TestCase>();
		cases.add(case1);
		cases.add(case2);
		cases.add(case3);
		testCases.setCases(cases);
		
		FileWriter writer = new FileWriter("test.xml");
		Marshaller.marshal(testCases, writer);
//		FileReader reader = new FileReader(new File("testcases.xml"));
//		String str = FileUtils.readFileToString(new File("testcases.xml"));
//		TestCases testCases = (TestCases)Unmarshaller.unmarshal(TestCases.class, reader);
//		System.out.println(testCases.getCases().size());
	}
	
	@Test
	public void testMarshalCustomargsToJson() throws Exception{
		CustomArgs args = new CustomArgs();
		args.setCourseId("CO1");
		args.setEnrollmentId("EO1");
		args.setLearningPathId("lp01");
		args.setMessagingQueueUrl("http://googlw.com");
		args.setSectionModuleInstanceId("secmodins01");
		args.setTenantId("2");
		args.setUserId("u01");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(args);
		System.out.println(json);
	}
	
	@Test
	public void testStack(){
		Stack<String> stk = new Stack<>();
		stk.add("1");
		stk.add("2");
		stk.add("3");
		stk.add("4");
		
			for(int i=0; i<12;i++){
				if(stk.size() == 0){
					stk.add("1");
					stk.add("2");
					stk.add("3");
					stk.add("4");
				}
				String disp = stk.pop();
					
				//stk.add(stk.size(), disp);
				System.out.println(disp);
			}
		
	}
	
	
	@Test
	public void testSamlResponseParse() throws IOException, SAXException, Exception{
		String str = FileUtils.readFileToString(new File("encoded.txt"));
		Base64Decoder  base64Decoder = new Base64Decoder();
		String decoded = new String(base64Decoder.decode(str));
		
		SamlResponseMiniDto dto = processXmlResp(decoded);
		System.out.println("dto @@@@2 firstname "+dto.getFirstName()+" last name  "+dto.getLastName()+" email "+dto.getEmail());
	}

	SamlResponseMiniDto processXmlResp(String xml) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		org.w3c.dom.Document document = builder.parse(new InputSource(new StringReader(xml)));
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		
		Element root = null;

        NodeList list = document.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
          if (list.item(i) instanceof Element) {
            root = (Element) list.item(i);
            break;
          }
        }
        root = document.getDocumentElement();
		root.normalize();
		System.out.println("Root element :" + document.getDocumentElement().getNodeName());
		String tag = "saml:Attribute";
		NodeList nList = document.getElementsByTagName(tag);
		SamlResponseMiniDto ret = new SamlResponseMiniDto();
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;
				System.out.println("attr name is "+element.getAttribute("Name"));
				if(element.getAttribute("Name").equals("firstName")){
					System.out.println(element.getNodeValue());
					System.out.println(element.getTextContent());
					ret.setFirstName(element.getTextContent());
				}
				else if(element.getAttribute("Name").equals("lastName")){
					System.out.println(element.getNodeValue());
					System.out.println(element.getTextContent());
					ret.setLastName(element.getTextContent());
				}
				else if(element.getAttribute("Name").equals("email")){
					System.out.println(element.getNodeValue());
					System.out.println(element.getTextContent());
					ret.setEmail(element.getTextContent());
				}
//				System.out.println(element.getTagName());
//				System.out.println(element.getNodeValue());
//				System.out.println(element.getLocalName());
//				System.out.println(element.getTextContent());
//				System.out.println("            ");
					
			}
		}
		
		return ret;
	}
	
	@Test
	public void testParseStr(){
		String u = "/infosys/yakshaspconsumerendpoint?companyId=IH&testId=37059&sharedDirect=yes&startDate=MTU4NDAzNzgwMDAwMA%3D%3D&endDate=MTg5OTU3MDYwMDAwMA%3D%3D";
		String keyvals = u.substring(u.indexOf("?")+1, u.length());
		System.out.println("keyvals "+keyvals);
		StringTokenizer stk = new StringTokenizer(keyvals, "&");
		String compId = "";
		String tid = "";
		String sharedD = "yes";
		String startd = "";
		String endd = "";
		while(stk.hasMoreTokens()){
			String st = stk.nextToken();
			if(st.contains("companyId")){
				compId = st.substring(st.indexOf("=")+1, st.length());
			}
			else if(st.contains("testId")){
				tid = st.substring(st.indexOf("=")+1, st.length());
			}
			else if(st.contains("startDate")){
				startd = st.substring(st.indexOf("=")+1, st.length());
			}
			else if(st.contains("endDate")){
				endd = st.substring(st.indexOf("=")+1, st.length());
			}
			
		}
	System.out.println("compid "+compId+" tid "+tid+" sd "+startd+" ed "+endd);	
	}

	
	@Test
	public void testCreateWorkspaceWithToken() throws Exception{
		String url = "https://che-che.eclipse11.yaksha.online";
		EclipseCheService eclipseCheService = new EclipseCheService();
		url += "/api/workspace/devfile?start-after-create=false&namespace=test";
 		eclipseCheService.setUrl(url);
		//String json = FileUtils.readFileToString(new File("assessment/eclipseChe/Java_FullStack.json"));
		
		String json = FileUtils.readFileToString(new File("assessment/eclipseChe/Java_FullStack_NewEclipseChe.json"));
		
		json = json.replace("${APP_USER}", "lemon"+System.currentTimeMillis());
		//json = json.replace("${APP_USER}", "a01");
		json = json.replace("${APP_DESC}", "Sample............................Project\n\n\n.........");
		User user = new User();
		user.setEmail("abc@def.com");
		user.setFirstName("abc");
		user.setLastName("def");
		user.setMobileNumber("111");
		String token = eclipseCheService.getAuthTokenKeyCloak("https://keycloak-che.eclipse11.yaksha.online/auth/realms/che/protocol/openid-connect/token", user);
		WorkspaceResponse workspaceResponse = eclipseCheService.createWorkSpace(json, token);
		System.out.println(workspaceResponse.getLinks().getIde());
		
	}
	
	@Test
	public void testReadJD() throws Exception{
		String str = FileUtils.readFileToString(new File("Job_Decription_Java_Developer.txt"));
		JobDescriptionParser parser = new JobDescriptionParserImpl();
		JobDescriptionParseResponseDto ret = parser.parseJobDesc(str);
		System.out.println(ret);
		
		
		
		
//		String regexJobDesc = "1.\\s*Job\\s*Description\\s*Description";
//		
//		String regexPrimrySkills = "2.\\s*Primary\\s*Skills\\s*[(]\\s*Comma\\s*Separated\\s*[)]";
//		
//		Pattern pattern = Pattern.compile(regexPrimrySkills, Pattern.CASE_INSENSITIVE);
//		Matcher matcher = pattern.matcher(str);
//			
//			
//			while (matcher.find())
//			{
//			    System.out.print("Start index: " + matcher.start());
//			    System.out.print(" End index: " + matcher.end() + " ");
//			    
//			    System.out.println( matcher.group());
//			}		
//		
//		String jobDescWithTitle = str.substring(0, str.indexOf("2.	Primary Skills (Comma Separated)"));
//		String jobDesc = jobDescWithTitle.substring("1.	Job Description Description".length(), jobDescWithTitle.length());
//		System.out.println(jobDesc.trim());
//		System.out.println("----------------");
//		
//		
//		
//		str = str.substring(jobDescWithTitle.length() ,str.length());
//		String primarySkillsWithTitle = str.substring(0, str.indexOf("3.	Secondary Skills (Comma Separated)"));
//		String primaryskills = primarySkillsWithTitle.substring("2.	Primary Skills (Comma Separated)".length(), primarySkillsWithTitle.length());
//		System.out.println(primaryskills.trim());
//		System.out.println("----------------");
//		
//		str = str.substring(primarySkillsWithTitle.length() ,str.length());
//		String secSkillsWithTitle = str.substring(0, str.indexOf("4.	Education Expectations (Comma Separated)"));
//		String secSkills = secSkillsWithTitle.substring("3.	Secondary Skills (Comma Separated)".length(), secSkillsWithTitle.length());
//		System.out.println(secSkills.trim());
//		System.out.println("----------------");
//		
//		str = str.substring(secSkillsWithTitle.length() ,str.length());
//		String edSkillsWithTitle = str.substring(0, str.indexOf("5.	Certification Expectations (Comma Separated)"));
//		String edSkills = edSkillsWithTitle.substring("4.	Education Expectations (Comma Separated)".length(), edSkillsWithTitle.length());
//		System.out.println(edSkills.trim());
//		System.out.println("----------------");
//		
//		str = str.substring(edSkillsWithTitle.length() ,str.length());
//		String cerSkillsWithTitle = str.substring(0, str.indexOf("6.	Domain Knowledge (Comma Separated)"));
//		String cerSkills = cerSkillsWithTitle.substring("5.	Certification Expectations (Comma Separated)".length(), cerSkillsWithTitle.length());
//		System.out.println(cerSkills.trim());
//		System.out.println("----------------");
//		
//		str = str.substring(cerSkillsWithTitle.length() ,str.length());
//		String domainSkillsWithTitle = str.substring(0, str.indexOf("7.	Experience - No of Years (Range)"));
//		String domainSkills = domainSkillsWithTitle.substring("6.	Domain Knowledge (Comma Separated)".length(), domainSkillsWithTitle.length());
//		System.out.println(domainSkills.trim());
//		System.out.println("----------------");
//		
//		str = str.substring(domainSkillsWithTitle.length() ,str.length());
//		String expWithTitle = str.substring(0, str.indexOf("8.	Location"));
//		String exp = expWithTitle.substring("7.	Experience - No of Years (Range)".length(), expWithTitle.length());
//		System.out.println(exp.trim());
//		System.out.println("----------------");
//		
//		str = str.substring(expWithTitle.length() ,str.length());
//		String locWithTitle = str.substring(0, str.length());
//		String loc = locWithTitle.substring("8.	Location".length(), locWithTitle.length());
//		System.out.println(loc.trim());
//		System.out.println("----------------");
	}
	
	@Test
	public void testRandom(){
		Random rand = new Random();
		int randomIndex1 = rand.nextInt((12 - 2) ) + 0;
		System.out.println(randomIndex1);
		
		randomIndex1 = rand.nextInt((12 - 2) ) + 0;
		System.out.println(randomIndex1);
		
		randomIndex1 = rand.nextInt((12 - 2) ) + 0;
		System.out.println(randomIndex1);
		
		randomIndex1 = rand.nextInt((12 - 2) ) + 0;
		System.out.println(randomIndex1);
		
		randomIndex1 = rand.nextInt((12 - 2) ) + 0;
		System.out.println(randomIndex1);
		
		randomIndex1 = rand.nextInt((12 - 2) ) + 0;
		System.out.println(randomIndex1);
		
		randomIndex1 = rand.nextInt((12 - 2) ) + 0;
		System.out.println(randomIndex1);
		
		randomIndex1 = rand.nextInt((12 - 2) ) + 0;
		System.out.println(randomIndex1);
		
		randomIndex1 = rand.nextInt((12 - 2) ) + 0;
		System.out.println(randomIndex1);
		
		randomIndex1 = rand.nextInt((12 - 2) ) + 0;
		System.out.println(randomIndex1);
	}
	
	@Test
	public void testRemoveStartNod(){
		String s1 = "1000 Aerodynamics";
		
		String s2 = "500Errrrrrrrr";
		
		String s3 = "eeeeee89";
		
		System.out.println(getNumbers(s1) +"  "+s1.replace(""+getNumbers(s1), ""));
		System.out.println(getNumbers(s2) +"  "+s2.replace(""+getNumbers(s2), ""));
		System.out.println(getNumbers(s3) +"  "+s3.replace(""+getNumbers(s3), ""));
	}
	
	public static String getNumbers(String s) {

	    String[] n = s.split(""); //array of strings
	    StringBuffer f = new StringBuffer(); // buffer to store numbers

	    for (int i = 0; i < n.length; i++) {
	        if((n[i].matches("[0-9]+"))) {// validating numbers
	            f.append(n[i]); //appending
	        }else {
	            //parsing to int and returning value
	            return f.toString();
	        }   
	    }
	    return "";
	 }
	
	@Test
	public void testCaseInsensitiveReplace(){
		String target = "1000 Aerodynamics MCQs";
		target = target.replaceAll("(?i)mcqs", "").trim();
		System.out.println(target);
	}
	
	
	@Test
	public void testGetBase64UrlEncoded(){
		String email = "john@doe.com";
		email = URLEncoder.encode(Base64.getEncoder().encodeToString(email.getBytes()));
		System.out.println(email);
	}
	
}
