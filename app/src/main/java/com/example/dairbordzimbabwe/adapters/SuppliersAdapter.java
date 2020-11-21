package com.example.dairbordzimbabwe.adapters;

import android.content.Context;

import com.example.dairbordzimbabwe.models.Supplier;

import java.util.List;

public class SuppliersAdapter {

    private List<Supplier> m_suppliers_list;
    private Context m_context;

    public SuppliersAdapter(List<Supplier> m_suppliers_list, Context m_context) {
        this.m_suppliers_list = m_suppliers_list;
        this.m_context = m_context;
    }
}
