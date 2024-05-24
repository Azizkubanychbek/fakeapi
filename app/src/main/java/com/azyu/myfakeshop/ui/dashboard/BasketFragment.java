package com.azyu.myfakeshop.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.azyu.myfakeshop.R;
import com.azyu.myfakeshop.databinding.FragmentBasketBinding;
import com.azyu.myfakeshop.models.ModelM;
import com.azyu.myfakeshop.ui.home.JemAdapter;

import java.util.ArrayList;

public class BasketFragment extends Fragment {

    private FragmentBasketBinding binding;
    private ArrayList<ModelM> basketProducts;
    private JemAdapter adapter;
    private NavController navController;
    private Double totalSum = 1.0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Use ViewModelProvider with this as Fragment
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentBasketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            basketProducts = getArguments().getParcelableArrayList("basket");
        }

        if (basketProducts != null && !basketProducts.isEmpty()) {
            binding.placeHolder.setVisibility(View.GONE);
            adapter = new JemAdapter(requireActivity(), basketProducts);
            binding.rvBasket.setAdapter(adapter);

            totalSum = 1.0;
            for (ModelM product : basketProducts) {
                totalSum += product.getPrice();
            }
            binding.basketTotalCount.setText(String.valueOf(totalSum - 1.0));
        } else {
            binding.placeHolder.setVisibility(View.VISIBLE);
            binding.basketTotalCount.setText("0.0");
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        binding.btnBack.setOnClickListener(v ->
                navController.navigate(R.id.action_navigation_basket_to_navigation_home)
        );

        binding.btnPay.setOnClickListener(v -> {
            totalSum = 0.0;
            navController.navigate(R.id.navigation_payment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
