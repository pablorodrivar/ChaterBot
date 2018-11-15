package org.ieszaidinvergeles.dam.chaterbot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static org.ieszaidinvergeles.dam.chaterbot.MainActivity.chatManager;

public class ChatLogs extends AppCompatActivity {
    public static final int CHAT_LOG_PICK = 1;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyRecyclerAdapter mAdapter;
    private List<Chat> chats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_logs);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String date = getIntent().getStringExtra("date");
        chats = chatManager.getContactos("",new String[]{});

        init();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyRecyclerAdapter(chats, new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Chat item) {
                goToChatLog(item);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void goToChatLog(Chat item) {
        Intent i = new Intent(ChatLogs.this, MainActivity.class);
        i.putExtra("chat", item);
        setResult(RESULT_OK, i);
        finish();
    }

}
