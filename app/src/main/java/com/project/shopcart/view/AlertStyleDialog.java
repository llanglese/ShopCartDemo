package com.project.shopcart.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.shopcart.R;
import com.project.shopcart.uitls.StringUtil;


/**通用对话框类
 * @author Lemon
 * @use new AlertDialog(...).show();
 */
public class AlertStyleDialog extends Dialog implements View.OnClickListener {
	//	private static final String TAG = "AlertDialog";

	/**
	 * 自定义Dialog监听器
	 */
	public interface OnDialogButtonClickListener {

		/**点击按钮事件的回调方法
		 * @param requestCode 传入的用于区分某种情况下的showDialog
		 * @param isPositive
		 */
		void onDialogButtonClick(int requestCode, boolean isPositive);
	}


	@SuppressWarnings("unused")
	private Context context;
	private String title;
	private String message;
	private String strPositive;
	private String strNegative;
	private boolean showNegativeButton = true;
	private int requestCode;
	private OnDialogButtonClickListener listener;

	/**
	 * 带监听器参数的构造函数
	 */
	public AlertStyleDialog(Context context, String title, String message, boolean showNegativeButton,
							int requestCode, OnDialogButtonClickListener listener) {
		super(context, R.style.AlertDialog);

		this.context = context;
		this.title = title;
		this.message = message;
		this.showNegativeButton = showNegativeButton;
		this.requestCode = requestCode;
		this.listener = listener;
	}
	public AlertStyleDialog(Context context, String title, String message, boolean showNegativeButton,
							String strPositive, int requestCode, OnDialogButtonClickListener listener) {
		super(context, R.style.AlertDialog);

		this.context = context;
		this.title = title;
		this.message = message;
		this.showNegativeButton = showNegativeButton;
		this.strPositive = strPositive;
		this.requestCode = requestCode;
		this.listener = listener;
	}
	public AlertStyleDialog(Context context, String title, String message,
							String strPositive, String strNegative, int requestCode, OnDialogButtonClickListener listener) {
		super(context, R.style.AlertDialog);

		this.context = context;
		this.title = title;
		this.message = message;
		this.strPositive = strPositive;
		this.strNegative = strNegative;
		this.requestCode = requestCode;
		this.listener = listener;  
	}

	private TextView tvTitle;
	private TextView tvMessage;
	private Button btnPositive;
	private Button btnNegative;
	@Override
	protected void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.layout_alert_style_dialog);
		setCanceledOnTouchOutside(true);

		tvTitle = (TextView) findViewById(R.id.tvAlertDialogTitle);
		tvMessage = (TextView) findViewById(R.id.tvAlertDialogMessage);
		btnPositive = (Button) findViewById(R.id.btnAlertDialogPositive);
		btnNegative = (Button) findViewById(R.id.btnAlertDialogNegative);

		tvTitle.setVisibility(StringUtil.isNotEmpty(title, true) ? View.VISIBLE : View.GONE);
		tvTitle.setText("" + StringUtil.getCurrentString());

		if (StringUtil.isNotEmpty(strPositive, true)) {
			btnPositive.setText(StringUtil.getCurrentString());
		}
		btnPositive.setOnClickListener(this);

		if (showNegativeButton) {
			if (StringUtil.isNotEmpty(strNegative, true)) {
				btnNegative.setText(StringUtil.getCurrentString());
			}
			btnNegative.setOnClickListener(this);
		} else {
			btnNegative.setVisibility(View.GONE);
		}

		tvMessage.setText(StringUtil.getTrimedString(message));
	}

	@Override
	public void onClick(final View v) {
		if (v.getId() == R.id.btnAlertDialogPositive) {
			listener.onDialogButtonClick(requestCode, true);
		} else if (v.getId() == R.id.btnAlertDialogNegative) {
			listener.onDialogButtonClick(requestCode, false);
		}

		dismiss();
	}

}

