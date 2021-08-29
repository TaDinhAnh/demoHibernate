package com.demo.sevrlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.entities.Account;
import com.demo.entities.Transactiondetails;
import com.demo.model.accountModel;
import com.demo.model.transactionModel;
import com.mysql.cj.result.LocalDateTimeValueFactory;

@WebServlet("/transaction")
public class transactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public transactionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			switch (action.toLowerCase()) {
			case "list":
				request.getRequestDispatcher("/WEB-INF/views/transaction/listTransaction.jsp").forward(request,
						response);
				break;
			default:
				break;
			}

		}
		request.getRequestDispatcher("/WEB-INF/views/transaction/transactionDetails.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			switch (action.toLowerCase()) {
			case "listtrans":
				doPost_ListTrans(request, response);
				break;
			default:
				doPost_Trans(request, response);
				break;
			}
		}
		doPost_Trans(request, response);

	}

	protected void doPost_Trans(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("transType").equals("1")) {
			doPost_Deposit(request, response);
		}
		doPost_WithDraw(request, response);
	}

	protected void doPost_ListTrans(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msg = null;
		String idAcc = request.getParameter("idAcc");
		Account acc = accountModel.find(idAcc);
		if (acc != null) {
			int typeTrans = Integer.parseInt(request.getParameter("typeTrans"));
			List<Transactiondetails> list = transactionModel.FindByIdAccAndTypeTrasn(idAcc, typeTrans);
			if (list != null) {
				request.setAttribute("list", list);
				request.getRequestDispatcher("/WEB-INF/views/transaction/listTransaction.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "tao chua co giao dich nao");
			}
		} else {
			request.setAttribute("msg", "tao khoan khong ton tai");
		}
		request.getRequestDispatcher("/WEB-INF/views/transaction/listTransaction.jsp").forward(request, response);
	}

	protected void doPost_WithDraw(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msg = null;
		String idAcc = request.getParameter("idAcc");
		BigDecimal transMoney = new BigDecimal(request.getParameter("money"));
		Account acc = accountModel.find(idAcc);
		if (acc != null) {
			if (acc.getBalance().compareTo(transMoney) > 0) {
				acc.setBalance(acc.getBalance().subtract(transMoney));
				if (accountModel.Update(acc)) {
					Transactiondetails trans = new Transactiondetails();
					trans.setAccId(idAcc);
					trans.setTranMoney(Double.parseDouble(transMoney.toString()));
					trans.setDateOfTrans(Date.valueOf(LocalDate.now()));
					trans.setTransType(2);
					if (transactionModel.Create(trans)) {
						request.setAttribute("msg", "thanh cong");
					} else {
						request.setAttribute("msg", "error database");
					}
				} else {
					request.setAttribute("msg", "error database");
				}
			} else {
				request.setAttribute("msg", "so du tai khoan khong du");
				request.getRequestDispatcher("/WEB-INF/views/transaction/transactionDetails.jsp").forward(request,
						response);
			}
		} else {
			request.setAttribute("msg", "tao khoan khong ton tai");
		}
		request.getRequestDispatcher("/WEB-INF/views/transaction/transactionDetails.jsp").forward(request, response);

	}

	protected void doPost_Deposit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msg = null;
		String idAcc = request.getParameter("idAcc");
		Account acc = accountModel.find(idAcc);
		if (acc != null) {
			BigDecimal transMoney = new BigDecimal(request.getParameter("money"));
			acc.setBalance(acc.getBalance().add(transMoney));
			if (accountModel.Update(acc)) {
				Transactiondetails trans = new Transactiondetails();
				trans.setAccId(idAcc);
				trans.setTranMoney(Double.parseDouble(transMoney.toString()));
				trans.setDateOfTrans(Date.valueOf(LocalDate.now()));
				trans.setTransType(1);
				if (transactionModel.Create(trans)) {
					request.setAttribute("msg", "thanh cong");
				} else {
					request.setAttribute("msg", "error database");
				}
			} else {
				request.setAttribute("msg", "error database");
			}
		} else {
			request.setAttribute("msg", "tao khoan khong ton tai");
		}
		request.getRequestDispatcher("/WEB-INF/views/transaction/transactionDetails.jsp").forward(request, response);
	}
}
