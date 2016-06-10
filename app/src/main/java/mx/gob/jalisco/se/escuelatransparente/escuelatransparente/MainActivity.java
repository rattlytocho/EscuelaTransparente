package mx.gob.jalisco.se.escuelatransparente.escuelatransparente;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkUtils utils;
        utils = new NetworkUtils(this);
        if(utils.isConnectingToInternet()){
            WebView webview = (WebView) findViewById(R.id.escuela_transparente);
            assert webview != null;
            webview.setWebChromeClient(new WebChromeClient());
            webview.setWebViewClient(new WebViewClient());
            webview.clearCache(true);
            webview.clearHistory();
            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

            webview.loadUrl("http://escuelatransparente.se.jalisco.gob.mx");

        }else{
            NointernetDialog();
        }

    }

    private void NointernetDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        builder.setTitle("No hay conexión");
        builder.setMessage(R.string.no_internet);
        builder.setCancelable(false);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.show();

    }
}
