package online.shatla_app.profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import online.shatla_app.R;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewProdectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewProdectFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
     private Spinner spinner;
private  String taxt;
    private EditText name,price,quantity,desciption ;

    private Button add;
    //private TextView display;
private ImageView product;
    private CircleImageView camera;
    private final int CAMERA_REQUEST=1;
    private Bitmap bitmap;
    private StorageReference storageReference;
    private FirebaseStorage firebaseStorage;
    private FirebaseDatabase firebaseDatabase;
    private  ProductItem productItem;

    private String mTital,mAuther,mdesciptiom;

    private DatabaseReference databaseReference;



    ArrayAdapter<CharSequence>arrayAdapter;
    private OnFragmentInteractionListener mListener;

    public NewProdectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewProdectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewProdectFragment newInstance(String param1, String param2) {
        NewProdectFragment fragment = new NewProdectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_new_prodect, container, false);

        name=view.findViewById(R.id.prodect_name);
        price=view.findViewById(R.id.price);
        quantity=view.findViewById(R.id.quantity);
        desciption=view.findViewById(R.id.description);
        spinner =(Spinner) view.findViewById(R.id.spinner);
        add = view.findViewById(R.id.add);
        product=view.findViewById(R.id.profile);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("product list");
        firebaseStorage=FirebaseStorage.getInstance();


        //spinner.setOnItemSelectedListener(this);
     arrayAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.prodect_type,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
spinner.setAdapter(arrayAdapter);
spinner.setOnItemSelectedListener(this);
       /* spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });*/


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bitmap!=null){





                    final int rend=(int)Math.floor(Math.random()*20);


                    storageReference=firebaseStorage.getReference("image/"+rend);

                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    byte[] data=baos.toByteArray();
                    UploadTask uploadTask=storageReference.putBytes(data);
                    Task<Uri>uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if(!task.isSuccessful()){
                                throw  task.getException();
                            }
                            else{
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        Log.d("TAG",uri.toString());
                                        productItem=new ProductItem();


                                        productItem.setName(name.getText().toString());
                                        productItem.setPrice(price.getText().toString());
                                        productItem.setQuantity(quantity.getText().toString());
                                        productItem.setInfo(desciption.getText().toString());
                                        productItem.setType(taxt);


                                        productItem.setImage(uri.toString());

                                        String key=databaseReference.push().getKey();
                                        databaseReference.child(key).setValue(productItem);

                                    }
                                });
                            }
                            return storageReference.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if(task.isSuccessful()){

                                Uri downloadUri= task.getResult();
                                String path=downloadUri.getPath();
                                Toast.makeText(getActivity(),path,Toast.LENGTH_LONG).show();
                            }else {

                            }


                        }
                    });


                }else {
                    Toast.makeText(getActivity(),"place capture or select image",Toast.LENGTH_LONG).show();
                }



            }
        });

        camera=view.findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST);
            }
        });



        return view;




    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        taxt=parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA_REQUEST&&resultCode==RESULT_OK){
            Bundle bundle=data.getExtras();
            bitmap=(Bitmap) bundle.get("data");
            product.setImageBitmap(bitmap);

        }
    }

}
