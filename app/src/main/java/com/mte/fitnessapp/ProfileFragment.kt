package com.mte.fitnessapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mte.fitnessapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference?=null
    var database: FirebaseDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance()
        databaseReference=database?.reference!!.child("profile")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var currentuser = auth.currentUser

        var userReference = databaseReference?.child(currentuser?.uid!!)
        userReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.editTextEposta.hint = snapshot.child("email").value.toString()
                binding.editTextAd.hint = snapshot.child("username").value.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
        binding.cikisYap.setOnClickListener { auth.signOut()
            val intent= Intent(requireContext(),LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        binding.deleteAccount.setOnClickListener {
            currentuser?.delete()?.addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(requireContext(),"Hesabınız Silindi",Toast.LENGTH_LONG).show()
                    auth.signOut()
                    val intent= Intent(requireContext(),LoginActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
            }

        }
    }
}