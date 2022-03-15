package sk.valovic.calculator

import android.os.Bundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.widget.HorizontalScrollView
import androidx.activity.ComponentActivity
import androidx.lifecycle.*
import sk.valovic.calculator.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val vm by viewModel { MainViewModel(it, this@MainActivity) }
        binding.vm = vm
        binding.lifecycleOwner = this

        vm.isCalculated.observe(this) {
            if(it) {
                binding.display.ellipsize = TextUtils.TruncateAt.END
            } else {
                binding.display.ellipsize = TextUtils.TruncateAt.START
            }
        }

    }
}

inline fun <reified VM : ViewModel> ComponentActivity.viewModel(crossinline create: (SavedStateHandle) -> VM) =
    ViewModelLazy(VM::class, { viewModelStore }) {
        object : AbstractSavedStateViewModelFactory(this, null) {
            override fun <T : ViewModel> create(
                key: String,
                type: Class<T>,
                handle: SavedStateHandle
            ): T =
                create(handle) as T
        }
    }