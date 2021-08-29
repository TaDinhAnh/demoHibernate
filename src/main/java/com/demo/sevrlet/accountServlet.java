package com.demo.sevrlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.entities.Account;
import com.demo.entities.Transactiondetails;
import com.demo.model.accountModel;
import com.demo.model.transactionModel;

@WebServlet("/account")
public class accountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public accountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/user/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String msg = null;
		String idAcc = request.getParameter("idAcc");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		Account acc = accountModel.find(idAcc);
		if (acc != null) {
			acc.setEmail(email);
			acc.setPhone(phone);
			if (accountModel.Update(acc)) {
				request.setAttribute("msg", "thanh cong");
			} else {
				request.setAttribute("msg", "error database");
			}
		} else {
			request.setAttribute("msg", "tao khoan khong ton tai");
		}
		request.getRequestDispatcher("/WEB-INF/views/transaction/transactionDetails.jsp").forward(request, response);
	}

}
