package com.ucne.ticketcompose.ui.documentos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.ticketcompose.data.remote.dto.DocumentosDto
import com.ucne.ticketcompose.data.repository.DocumentosRepository
import com.ucne.ticketcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DocumentosUiState(
    var documentos: List<DocumentosDto> = emptyList(),
    var isLoading: Boolean = false,
    var error: String? = null
)

@HiltViewModel
class DocumentosViewModel @Inject constructor(
    private val documentosRepository: DocumentosRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DocumentosUiState())
    val uiState: StateFlow<DocumentosUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            documentosRepository.GetDocumentos().collectLatest { result ->
                when (result) {
                    is Resource.Loading -> _uiState.update {
                        it.copy(isLoading = true)
                    }

                    is Resource.Success -> _uiState.update {
                        it.copy(documentos = result.data ?: emptyList(), isLoading = false)
                    }

                    is Resource.Error -> _uiState.update {
                        it.copy(documentos = emptyList(),isLoading = false, error = result.message ?: "error")
                    }
                }
            }
        }

       /* .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DocumentosUiState()
        )*/
    }
}