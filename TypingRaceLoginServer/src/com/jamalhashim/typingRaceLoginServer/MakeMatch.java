package com.jamalhashim.typingRaceLoginServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.CoreProtocolPNames;

/**
 * Servlet implementation class MakeMatch
 */
@WebServlet("/MakeMatch")
public class MakeMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeMatch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DATA data = new DATA();
		PrintWriter out = response.getWriter();
		if(request.getParameter("loginKey").equals(data.loginKey1)) {
			UUID sessionID = UUID.randomUUID();
			String username = data.username1;
			Thread send = new Thread(new Runnable() {

				@Override
				public void run() {
					submitRequest(sessionID.toString(), username);
				}
				
			});
			send.start();
			out.append(sessionID.toString());
			return;
		}
		if(request.getParameter("loginKey").equals(data.loginKey2)) {
			UUID sessionID = UUID.randomUUID();
			String username = data.username2;
			Thread send = new Thread(new Runnable() {

				@Override
				public void run() {
					submitRequest(sessionID.toString(), username);
				}
				
			});
			send.start();
			out.append(sessionID.toString());
			return;
		}
		out.append("invalid login key");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	void submitRequest(String sessionID, String username) {
		HttpClient client = HttpClientBuilder.create().build();
		int port = 9091;
		String url = "http://localhost:"+port+"?";
		url+="username="+username;
		url+="&sessionID="+sessionID;
		url+="&type=match";
		HttpGet request = new HttpGet(url);
		request.addHeader("User-Agent", CoreProtocolPNames.USER_AGENT);
		HttpResponse response = null;
		try {
			response = client.execute(request);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
