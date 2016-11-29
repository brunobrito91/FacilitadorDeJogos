package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.R;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Activity context;
    private Map<String, List<String>> opcoesCollections;
    private List<String> opcoes;

    public ExpandableListAdapter(Activity context, List<String> opcoes, Map<String, List<String>> opcoesCollections) {
        this.context = context;
        this.opcoesCollections = opcoesCollections;
        this.opcoes = opcoes;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return opcoesCollections.get(opcoes.get(groupPosition)).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String itemOpcao = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_child_item, null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.itemOpcao);
        item.setText(itemOpcao);
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return opcoesCollections.get(opcoes.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return opcoes.get(groupPosition);
    }

    public int getGroupCount() {
        return opcoes.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String opcaoName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.activity_group_item,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.opcao);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(opcaoName);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}