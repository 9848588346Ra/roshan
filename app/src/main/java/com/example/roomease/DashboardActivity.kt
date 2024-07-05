package com.example.roomease.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomease.Adapter.PopularAdapter
import com.example.roomease.Domains.PopularDomain
import com.example.roomease.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    lateinit var dashboardBinding: ActivityDashboardBinding
    lateinit var adapterPopular: PopularAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        dashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(dashboardBinding.root)
        initRecyclerView()

        dashboardBinding.btnBudget.setOnClickListener {
            intent = Intent(this@DashboardActivity,BudgetActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        dashboardBinding.btnreview.setOnClickListener {
            intent = Intent(this@DashboardActivity,Reviewsandrating::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        val items = ArrayList<PopularDomain>()
        items.add(PopularDomain(
            "Pokhara",
            "Phewa Lake",
            "Pokhara, one of most popular tourist destinations, is renowned for its stunning natural beauty. " +
                    "Nestled in the lap of the Himalayas, it offers breathtaking views of snow-capped mountains. " +
                    "The city is home to Phewa Lake, where visitors can enjoy boating and see the reflection of the surrounding mountains.",
            4.8,
            "pokhara"
        ))
        items.add(PopularDomain(
            "Chitwan",
            "Chitwan National Park",
            "Chitwan, is famous for Chitwan National Park, a UNESCO World Heritage Site. " +
                    "The park offers jungle safaris where visitors can spot Bengal tigers, one-horned rhinoceroses, and elephants. " +
                    "Chitwan is a perfect destination for wildlife enthusiasts and cultural explorers alike.",
            4.8,
            "chitwan"
        ))
        items.add(PopularDomain(
            "Lumbini",
            "MayaDevi Temple",
            "Lumbini, in southern Nepal, is the birthplace of Buddha. " +
                    "It's a UNESCO World Heritage Site known for its sacred Mayadevi Temple and peaceful gardens, attracting pilgrims and visitors seeking spiritual connection and tranquility.",
            4.8,
            "lumbini"
        ))
        items.add(PopularDomain(
            "Ghandruk",
            "Ghale gau",
            "Ghandruk, is famed for its breathtaking mountain views and rich Gurung culture. " +
                    "A favorite stop on the Annapurna Circuit trek, it offers stunning vistas of peaks like Machapuchare (Fishtail). " +
                    "Visitors can enjoy traditional stone houses, local hospitality, and the peaceful mountain setting of Ghandruk.",
            4.8,
            "ghandruk"
        ))

        val recyclerViewPopular = dashboardBinding.recyclerViewPopular
        recyclerViewPopular.layoutManager = LinearLayoutManager(this)
        adapterPopular = PopularAdapter(items)
        recyclerViewPopular.adapter = adapterPopular
    }
}
